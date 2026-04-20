package main

import (
	"bufio"
	"context"
	"errors"
	"fmt"
	"grpc-demo/webchatpb"
	"io"
	"os"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

const (
	clientLanguage = "Go"
	nickname       = "George"
	chatRoomId     = "My Cool Room For Cool People"
	exitCommand    = "/exit"
)

func main() {
	conn, err := grpc.NewClient("localhost:9090", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		panic(err)
	}
	defer conn.Close()

	client := webchatpb.NewWebChatClient(conn)
	chatRoom := &webchatpb.ChatRoom{ChatRoomId: chatRoomId}

	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	// Start the bi-directional Stream (Client <> Server)
	stream, err := client.JoinStreamSession(ctx)
	if err != nil {
		panic(err)
	}

	// Receive messages from the server in the background
	go func() {
		for {
			// Consume a message from the stream (Server -> Client)
			msg, err := stream.Recv()
			if err == io.EOF || errors.Is(err, context.Canceled) {
				return
			}
			if err != nil {
				fmt.Printf("Stream closed: %v\n", err)
				return
			}
			fmt.Printf("[%s] %s (%s): %s\n",
				msg.ChatRoom.ChatRoomId,
				msg.Nickname,
				msg.ClientLanguage,
				msg.Message,
			)
		}
	}()

	//Initialize by sending empty message (Client -> Server "HANDSHAKE")
	err = stream.Send(&webchatpb.ChatMessage{
		ChatRoom:                 chatRoom,
		Message:                  "HELLO WORLD",
		TimeGeneratedEpochMillis: time.Now().UnixMilli(),
		Nickname:                 nickname,
		ClientLanguage:           clientLanguage,
	})
	if err != nil {
		panic(err)
	}

	// Send messages typed by the user
	fmt.Printf("Joined \"%s\". Input \"%s\" to exit...\n", chatRoomId, exitCommand)
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		text := scanner.Text()

		// Clear the line just typed
		fmt.Print("\033[1A\033[2K")

		if text == exitCommand {
			break
		}

		// Send messages over bi-directional stream (Client -> Server)
		err := stream.Send(&webchatpb.ChatMessage{
			ChatRoom:                 chatRoom,
			Message:                  text,
			TimeGeneratedEpochMillis: time.Now().UnixMilli(),
			Nickname:                 nickname,
			ClientLanguage:           clientLanguage,
		})
		if err != nil {
			fmt.Printf("Send error: %v\n", err)
			break
		}
	}

	stream.CloseSend()
}
