using System;
using System.Text.Json;
using System.Threading.Channels;
using System.Threading.Tasks;
using Grpc.Core;
using GrpcDemoDotnet.WebChat.Server;
using Webchat;

namespace GrpcDemoDotnet.WebChat
{
    // Inherit from the Proto-generated gRPC class so we can define the methods
    public class WebChatServiceImpl : Webchat.WebChat.WebChatBase
    {
        // Use this to pretty-print the json we receive in the console so it's easier to read.
        private JsonSerializerOptions options = new()
        {
            WriteIndented = true,
        };
        
        
        private static void WriteColored(string message, ConsoleColor color)
        {
            Console.ForegroundColor = color;
            Console.WriteLine(message);
            Console.ResetColor();
        }

        // ChatRoomHub maintains channels for asynchronous message updates for each chatroom.
        private static readonly ChatRoomHub ChatRoomHub = new();

        // Unary call; sends a single message to a specified room
        public override async Task<SendReceipt> SendMessage(ChatMessage message, ServerCallContext context)
        {
            WriteColored($"[{DateTime.Now:HH:mm:ss:fff}][(Unary) RECEIVING]:\n{JsonSerializer.Serialize(message, options)}\n", ConsoleColor.Green);
            await ChatRoomHub.PublishAsync(message.ChatRoom.ChatRoomId, message, context.CancellationToken);

            return new SendReceipt { SentSuccessfully = true };
        }

        // Server-side streaming; sends stream of messages to the client as they are added
        public override async Task JoinChatRoom(
            ChatRoom request,
            IServerStreamWriter<ChatMessage> responseStream,
            ServerCallContext context
        )
        {
            // Listen for new messages in the room
            ChannelReader<ChatMessage> reader = ChatRoomHub.Subscribe(request.ChatRoomId);
            await foreach (ChatMessage message in reader.ReadAllAsync(context.CancellationToken))
            {
                await responseStream.WriteAsync(message);
            }
        }

        // Client-side streaming; sends stream of messages to the server as they are added
        public override async Task<SendReceipt> StreamMessagesToServer(
            IAsyncStreamReader<ChatMessage> requestStream,
            ServerCallContext context
        )
        {
            Console.WriteLine("Opening stream with client...");

            await foreach (ChatMessage message in requestStream.ReadAllAsync())
            {
                WriteColored($"[{DateTime.Now:HH:mm:ss:fff}][(Stream Client>>Server) RECEIVED]:\n{JsonSerializer.Serialize(message, options)}\n", ConsoleColor.Blue);
                await ChatRoomHub.PublishAsync(message.ChatRoom.ChatRoomId, message, context.CancellationToken);
            }

            return new SendReceipt { SentSuccessfully = true };
        }

        // Bidirectional streaming; two streams open between client and server for communication
        public override async Task JoinStreamSession(
            IAsyncStreamReader<ChatMessage> requestStream,
            IServerStreamWriter<ChatMessage> responseStream,
            ServerCallContext context
        )
        {
            Console.WriteLine("Starting bidirectional stream with client...");

            // Read the first message to determine which room this client is joining
            if (!await requestStream.MoveNext(context.CancellationToken))
                return;

            ChatMessage current = requestStream.Current;
            string roomId = current.ChatRoom.ChatRoomId;
            
            // Swallow the first message since it's acting as a "handshake"
            await ChatRoomHub.PublishAsync(roomId, new ChatMessage
            {
                ChatRoom = current.ChatRoom,
                ClientLanguage = "SYSTEM",
                Nickname = "SERVER",
                Message = $"{current.Nickname} joined the room!",
                TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()
            }, context.CancellationToken);

            // Subscribe to the room and forward new messages to the response stream
            ChannelReader<ChatMessage> reader = ChatRoomHub.Subscribe(roomId);
            Task forwardTask = Task.Run(async () =>
            {
                await foreach (ChatMessage message in reader.ReadAllAsync(context.CancellationToken))
                {
                    await responseStream.WriteAsync(message);
                }
            });

            // Publish each subsequent incoming message to the hub
            await foreach (ChatMessage message in requestStream.ReadAllAsync(context.CancellationToken))
            {
                WriteColored($"[{DateTime.Now:HH:mm:ss:fff}][(Stream Client<>Server) RECEIVED]:\n{JsonSerializer.Serialize(message, options)}\n", ConsoleColor.Magenta);
                await ChatRoomHub.PublishAsync(message.ChatRoom.ChatRoomId, message, context.CancellationToken);
            }

            await forwardTask;
        }
    }
}