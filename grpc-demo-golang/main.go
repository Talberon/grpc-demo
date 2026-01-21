package main

import (
	"context"
	"fmt"
	"grpc-demo/webchatpb"
	"time"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

func main() {
	serverAddr := "localhost:9090"
	opts := []grpc.DialOption{
		grpc.WithTransportCredentials(insecure.NewCredentials()),
	}
	conn, err := grpc.NewClient(serverAddr, opts...)
	if err != nil {
		panic(err)
	}
	webchatClient := webchatpb.NewWebChatClient(conn)

	// Send a message
	receipt, err := webchatClient.SendMessage(context.Background(), &webchatpb.ChatMessage{
		ChatRoom: &webchatpb.ChatRoom{
			ChatRoomId: "My Cool Room For Cool People",
		},
		Message:                  "Let's a-go!",
		TimeGeneratedEpochMillis: time.Now().UnixMilli(),
		Nickname:                 "George",
		ClientLanguage:           "Go",
	})
	if err != nil {
		panic(err)
	}
	if receipt.SentSuccessfully {
		fmt.Println("Sent message successfully!")
	} else {
		fmt.Println("Failed to send message!")
	}
	defer conn.Close()
}
