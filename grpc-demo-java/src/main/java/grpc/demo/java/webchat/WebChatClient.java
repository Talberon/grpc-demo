package grpc.demo.java.webchat;

import grpc.demo.java.webchat.proto.WebChatGrpc;
import grpc.demo.java.webchat.proto.WebChatGrpc.WebChatBlockingStub;
import grpc.demo.java.webchat.proto.WebChatProto.ChatMessage;
import grpc.demo.java.webchat.proto.WebChatProto.ChatRoom;
import io.grpc.ManagedChannelBuilder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WebChatClient {

  private static Thread roomThread;

  public static void main(String[] args) throws InterruptedException {
    var channel = ManagedChannelBuilder
        .forAddress("localhost", 9090)
        .usePlaintext() //No encryption
        .build();
    var client = WebChatGrpc.newBlockingStub(channel); //Synchronous calls to the service
    System.out.println("Started client on " + channel.authority() + "...");

    var chatRoom = ChatRoom.newBuilder().setChatRoomId("My Cool Room For Cool People").build();

    StartChatRoomSession(client, chatRoom);
    SendMessagesToRoom(client, chatRoom);

    Thread.sleep(1_000_000);
    roomThread.interrupt();
    System.out.println("Shutting down...");
  }

  private static void StartChatRoomSession(WebChatBlockingStub client, ChatRoom chatRoom) {
    roomThread = new Thread(() -> {
      System.out.println("Joining chat room: " + chatRoom.getChatRoomId());
      var session = client.joinChatRoom(chatRoom);

      while (true) {
        if (session.hasNext()) {
          var nextMessage = session.next();
          System.out.println(String.format(
              "[%s] %s (%s): %s",
              nextMessage.getChatRoom().getChatRoomId(),
              nextMessage.getNickname(),
              nextMessage.getClientLanguage(),
              nextMessage.getMessage()
          ));
        }
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
          return;
        }
      }
    });

    roomThread.start();
  }

  private static void SendMessagesToRoom(WebChatBlockingStub client, ChatRoom chatRoom) {
    System.out.println("Sending messages to room: " + chatRoom.getChatRoomId() + "...");

    client.sendMessage(ChatMessage.newBuilder()
        .setChatRoom(chatRoom)
        .setMessage("I love coffee!")
        .setNickname("Charlie")
        .setClientLanguage("Java")
        .setTimeGeneratedEpochMillis(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        .build()
    );

    client.sendMessage(ChatMessage.newBuilder()
        .setChatRoom(chatRoom)
        .setMessage("I'm going to go buy some!")
        .setNickname("Charlie")
        .setClientLanguage("Java")
        .setTimeGeneratedEpochMillis(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        .build()
    );
  }
}
