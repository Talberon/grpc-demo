using System;
using System.Threading.Channels;
using System.Threading.Tasks;
using Grpc.Core;
using GrpcDemoDotnet.WebChat.Server;
using Webchat;

namespace GrpcDemoDotnet.WebChat
{
    //Inherit from the Proto-generated gRPC class so we can define the methods
    public class WebChatServiceImpl : Webchat.WebChat.WebChatBase
    {
        private static readonly ChatRoomHub ChatRoomHub = new();

        //Unary call; sends a single message to a specified room
        public override async Task<SendReceipt> SendMessage(ChatMessage request, ServerCallContext context)
        {
            Console.WriteLine($"[(Unary) RECEIVING]: {request}");
            await ChatRoomHub.PublishAsync(request.ChatRoom.ChatRoomId, request, context.CancellationToken);

            return new SendReceipt { SentSuccessfully = true };
        }

        //Server-side streaming; sends stream of messages to the client as they are added
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

        //Client-side streaming; sends stream of messages to the server as they are added
        public override async Task<SendReceipt> StreamMessagesToServer(
            IAsyncStreamReader<ChatMessage> requestStream,
            ServerCallContext context
        )
        {
            Console.WriteLine("Opening stream with client...");

            await foreach (ChatMessage message in requestStream.ReadAllAsync())
            {
                Console.WriteLine($"[(Client -> Server) RECEIVED]: {message}");
                await ChatRoomHub.PublishAsync(message.ChatRoom.ChatRoomId, message, context.CancellationToken);
            }

            return new SendReceipt { SentSuccessfully = true };
        }

        //Bidirectional streaming; two streams open between client and server for communication
        public override async Task JoinStreamSession(
            IAsyncStreamReader<ChatMessage> requestStream,
            IServerStreamWriter<ChatMessage> responseStream,
            ServerCallContext context
        )
        {
            Console.WriteLine("Starting bidirectional stream with client...");

            while (!context.CancellationToken.IsCancellationRequested)
            {
                await foreach (ChatMessage requestMessage in requestStream.ReadAllAsync())
                {
                    Console.WriteLine($"[(Client <-> Server) RECEIVED]: {requestMessage}");

                    var timeMessage = new ChatMessage
                    {
                        TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds(),
                        Message = $"The mockingbird says: {requestMessage.Message}",
                        Nickname = "Time Server",
                        ClientLanguage = "C#",
                        ChatRoom = requestMessage.ChatRoom
                    };

                    Console.WriteLine($"[(Client <-> Server) SENDING]: {timeMessage}");
                    await responseStream.WriteAsync(timeMessage);
                }
            }
        }
    }
}