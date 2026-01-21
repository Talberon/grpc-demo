using System;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;
using Webchat;

namespace grpc_demo_dotnet.WebChat
{
    public static class WebChatClient
    {
        private static CancellationTokenSource _cancellationTokenSource;

        public static void Run()
        {
            Console.WriteLine("Starting gRPC client...");

            //Connect to the server
            var channel = new Channel("localhost", 9090, ChannelCredentials.Insecure);
            var client = new Webchat.WebChat.WebChatClient(channel);

            //Define the chat room we want to join
            var chatRoom = new ChatRoom { ChatRoomId = "My Cool Room For Cool People" };

            //Run chat room on a separate thread
            Console.WriteLine($"Connecting to chat room: {chatRoom}...");
            var chatThread = new Thread(() => JoinRoomAndSendMessages(client, chatRoom).Wait());
            chatThread.Start();

            Console.WriteLine("Press ENTER to exit...");
            Console.ReadLine(); //Wait for user input
            _cancellationTokenSource.Cancel(); //Causes a graceful exit
        }

        private static async Task SendStreamOfMessagesToServer(
            Webchat.WebChat.WebChatClient client,
            ChatMessage[] messages
        )
        {
            AsyncClientStreamingCall<ChatMessage, SendReceipt> call = client.StreamMessagesToServer();

            Console.WriteLine($">> Streaming {messages.Length} messages to the chat server...");
            foreach (ChatMessage message in messages)
            {
                await call.RequestStream.WriteAsync(message);
            }

            await call.RequestStream.CompleteAsync();
            Console.WriteLine($">> Streamed {messages.Length} messages to the chat server!");

            SendReceipt receipt = await call.ResponseAsync;
            Console.WriteLine(
                "#######################################\n" +
                "# SERVER RESPONSE: \n" +
                $"# Sent Successfully?: {receipt.SentSuccessfully}\n" +
                "#######################################"
            );
        }

        private static async Task JoinRoomAndSendMessages(Webchat.WebChat.WebChatClient client, ChatRoom chatRoom)
        {
            AsyncServerStreamingCall<ChatMessage> chatRoomStream = client.JoinChatRoom(chatRoom);

            //Send a couple of chat messages to the server
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

            await SendStreamOfMessagesToServer(client, [
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = "C# (Streamed)",
                    Message = "I was streamed by a client (1)!",
                    Nickname = "Bob",
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                },
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = "C# (Streamed)",
                    Message = "I was streamed by a client (2)!",
                    Nickname = "Bob",
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                },
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = "C# (Streamed)",
                    Message = "I was streamed by a client (3)!",
                    Nickname = "Bob",
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                }]);

            //Use a cancellation token for graceful stream ending
            _cancellationTokenSource = new CancellationTokenSource();
            try
            {
                await foreach (ChatMessage message in chatRoomStream.ResponseStream.ReadAllAsync(
                                   _cancellationTokenSource.Token))
                {
                    //Print the chat messages from the server as they come in, formatted prettily
                    Console.WriteLine(
                        $"[{message.ChatRoom.ChatRoomId}] {message.Nickname} ({message.ClientLanguage}): {message.Message}"
                    );
                }
            }
            catch (RpcException e) when (e.Status.StatusCode == StatusCode.Cancelled)
            {
                Console.WriteLine("Streaming cancelled from client side.");
            }
        }
    }
}