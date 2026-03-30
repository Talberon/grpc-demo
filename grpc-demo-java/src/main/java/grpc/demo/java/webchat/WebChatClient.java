package grpc.demo.java.webchat;

import grpc.demo.java.webchat.proto.WebChatGrpc;
import grpc.demo.java.webchat.proto.WebChatGrpc.WebChatBlockingStub;
import grpc.demo.java.webchat.proto.WebChatProto.ChatMessage;
import grpc.demo.java.webchat.proto.WebChatProto.ChatRoom;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WebChatClient {

  private static Thread roomThread;

  void main() throws IOException {
    // Connect to the server.
    var channel = ManagedChannelBuilder
        .forAddress("localhost", 9090)
        .usePlaintext() //No encryption
        .build();
    var client = WebChatGrpc.newBlockingStub(channel); //Synchronous calls to the service
    System.out.println("Started client on " + channel.authority() + "...");

    // Define the chat room we want to join.
    var chatRoom = ChatRoom.newBuilder().setChatRoomId("My Cool Room For Cool People").build();

    // Start the room.
    StartChatRoomSession(client, chatRoom);

    // Send some messages after we connect (no user-input; just hard-coded samples).
    SendMessagesToRoom(client, chatRoom);

    // Block shutdown until we enter something.
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(inputStreamReader);
    System.out.println("Press ENTER to shut down...");
    reader.readLine();

    System.out.println("Shutting down...");
    roomThread.interrupt();
    try {
      roomThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    channel.shutdownNow();
  }

  private static void StartChatRoomSession(WebChatBlockingStub client, ChatRoom chatRoom) {
    // Run this room async on another thread.
    roomThread = new Thread(() -> {
      System.out.println("Joining chat room: " + chatRoom.getChatRoomId());
      var session = client.joinChatRoom(chatRoom);

      while (!Thread.currentThread().isInterrupted()) {
        try {
          if (session.hasNext()) {
            // Print the next message received from the server to the console.
            var nextMessage = session.next();
            System.out.println(String.format(
                "[%s] %s (%s): %s",
                nextMessage.getChatRoom().getChatRoomId(),
                nextMessage.getNickname(),
                nextMessage.getClientLanguage(),
                nextMessage.getMessage()
            ));
          }

          // Sleep for a little bit so we don't hammer the CPU.
          Thread.sleep(200);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        } catch (StatusRuntimeException e) {
          // Channel was shut down, exit cleanly.
          return;
        }
      }
    });

    roomThread.start();
  }

  private static void SendMessagesToRoom(WebChatBlockingStub client, ChatRoom chatRoom) {
    System.out.println("Sending messages to room: " + chatRoom.getChatRoomId() + "...");

    var _ = client.sendMessage(ChatMessage.newBuilder()
        .setChatRoom(chatRoom)
        .setMessage("I love coffee!")
        .setNickname("Charlie")
        .setClientLanguage("Java")
        .setTimeGeneratedEpochMillis(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        .build()
    );

    var _ = client.sendMessage(ChatMessage.newBuilder()
        .setChatRoom(chatRoom)
        .setMessage("I'm going to go buy some!")
        .setNickname("Charlie")
        .setClientLanguage("Java")
        .setTimeGeneratedEpochMillis(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        .build()
    );
  }
}
