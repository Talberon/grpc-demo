using System;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;
using Webchat;

namespace grpc_demo_dotnet.WebChat
{
    public static class WebChatClient
    {
        public static void Run()
        {
            Console.WriteLine("Starting gRPC client...");

            var channel = new Channel("localhost", 9090, ChannelCredentials.Insecure);
            var client = new Webchat.WebChat.WebChatClient(channel);


            var chatRoom = new ChatRoom {ChatRoomId = "My Cool Room For Cool People"};


            Console.WriteLine($"Connecting to chat room: {chatRoom}...");
            try
            {
                JoinRoomAndSendMessages(client, chatRoom).Wait();
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occurred: {0}", e);
            }
        }

        private static async Task JoinRoomAndSendMessages(Webchat.WebChat.WebChatClient client, ChatRoom chatRoom)
        {
            AsyncServerStreamingCall<ChatMessage> chatRoomStream = client.JoinChatRoom(chatRoom);

            client.SendMessage(new ChatMessage
            {
                Message = "Hello world!",
                ChatRoom = chatRoom,
                ClientLanguage = "C#",
                Nickname = "Alice",
                TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
            });

            client.SendMessage(new ChatMessage
            {
                Message = "This is my second message!",
                ChatRoom = chatRoom,
                ClientLanguage = "C#",
                Nickname = "Alice",
                TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
            });

            Thread.Sleep(TimeSpan.FromMilliseconds(1000));


            await foreach (ChatMessage message in chatRoomStream.ResponseStream.ReadAllAsync())
            {
                Console.WriteLine(
                    $"[{message.ChatRoom.ChatRoomId}] {message.Nickname} ({message.ClientLanguage}): {message.Message}"
                );
            }
        }
    }
}