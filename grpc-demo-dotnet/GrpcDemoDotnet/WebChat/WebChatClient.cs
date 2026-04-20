using System;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;
using Webchat;

namespace GrpcDemoDotnet.WebChat
{
    public static class WebChatClient
    {
        private static CancellationTokenSource _cancellationTokenSource;
        private const string ClientLanguage = "C#";
        private const string Nickname = "Charlie";
        
        public static async Task Run()
        {
            Console.WriteLine("Starting gRPC client...");
            //Connect to the server
            var channel = new Channel("localhost", 9090, ChannelCredentials.Insecure);
            var client = new Webchat.WebChat.WebChatClient(channel);

            await StreamDemoMode(client); // For use in client-side streaming sessions
            // await UserMode(client); // For use in interactive sessions
        }
        
        private static async Task StreamDemoMode(Webchat.WebChat.WebChatClient client)
        {
            //Define the chat room we want to join
            var chatRoom = new ChatRoom { ChatRoomId = "My Cool Room For Cool People" };
            
            //Stream messages from Client -> Server
            await StreamMessagesToRoom(client, chatRoom);
        }

        private static async Task UserMode(Webchat.WebChat.WebChatClient client)
        {
            //Define the chat room we want to join
            var chatRoom = new ChatRoom { ChatRoomId = "My Cool Room For Cool People" };
            //Join the room so we can see new messages
            _ = JoinRoom(client, chatRoom); //Do not block.
            
            //Stream messages from Client -> Server
            await StreamMessagesToRoom(client, chatRoom);
            
            //Accept user input
            const string exitCommand = "/exit";
            Console.WriteLine($"Input \"{exitCommand}\" to exit...");
            string lastInput;
            do
            {
                lastInput = Console.ReadLine();
                Console.Write("\e[1A\e[2K"); //Clear the current line
                
                SendMessageToRoom(client, lastInput, chatRoom);
                
            } while (lastInput != exitCommand);
            await _cancellationTokenSource.CancelAsync(); //Exit gracefully
        }

        private static void SendMessageToRoom(Webchat.WebChat.WebChatClient client, string message, ChatRoom room)
        {
            client.SendMessage(new ChatMessage
            {
                Message = message,
                ChatRoom = room,
                ClientLanguage = ClientLanguage,
                Nickname = Nickname,
                TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
            });
        }

        private static async Task StreamMessagesToRoom(Webchat.WebChat.WebChatClient client, ChatRoom chatRoom)
        {
            await SendStreamOfMessagesToServer(client, [
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = ClientLanguage,
                    Message = "I was streamed by a client (1)!",
                    Nickname = Nickname,
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                },
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = ClientLanguage,
                    Message = "I was streamed by a client (2)!",
                    Nickname = Nickname,
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                },
                new ChatMessage
                {
                    ChatRoom = chatRoom,
                    ClientLanguage = ClientLanguage,
                    Message = "I was streamed by a client (3)!",
                    Nickname = Nickname,
                    TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
                }
            ]);
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

        private static async Task JoinRoom(Webchat.WebChat.WebChatClient client, ChatRoom chatRoom)
        {
            AsyncServerStreamingCall<ChatMessage> chatRoomStream = client.JoinChatRoom(chatRoom);
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