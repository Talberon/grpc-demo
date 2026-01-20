using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;
using Webchat;

namespace grpc_demo_dotnet.WebChat
{
    public class WebChatServiceImpl : Webchat.WebChat.WebChatBase
    {
        private static readonly TimeSpan HeartbeatInterval = TimeSpan.FromMilliseconds(500);

        //Create an in-memory collection of rooms
        private readonly Dictionary<string, ChatRoomLog> _chatRooms = new();

        //Unary call; sends a single message to a specified room
        public override Task<SendReceipt> SendMessage(ChatMessage message, ServerCallContext context)
        {
            if (!_chatRooms.TryGetValue(message.ChatRoom.ChatRoomId, out ChatRoomLog value))
            {
                Console.WriteLine($"Chat room {message.ChatRoom.ChatRoomId} not found!");
                return Task.FromResult(new SendReceipt()
                {
                    SentSuccessfully = false
                });
            }

            Console.WriteLine($"Adding message {message} to room...");
            value.MessageLog.Add(message);

            return Task.FromResult(new SendReceipt()
            {
                SentSuccessfully = true
            });
        }

        //Server-side streaming; sends stream of messages to the client as they are added
        public override async Task JoinChatRoom(
            ChatRoom chatRoom,
            IServerStreamWriter<ChatMessage> responseStream,
            ServerCallContext context
        )
        {
            ChatRoomLog currentRoomLog = _chatRooms.TryGetValue(chatRoom.ChatRoomId, out ChatRoomLog room)
                ? room
                : _chatRooms[chatRoom.ChatRoomId] = new ChatRoomLog([]);

            Console.WriteLine($"Joining chat room '{chatRoom.ChatRoomId}'...");

            int previousMessageCount = currentRoomLog.MessageLog.Count;

            //Loop until client disconnects
            while (!context.CancellationToken.IsCancellationRequested)
            {
                Thread.Sleep(HeartbeatInterval);

                //TODO Handle if messages are edited (if we add that functionality)
                int messageChange = previousMessageCount - currentRoomLog.MessageLog.Count;

                if (messageChange == 0) continue;

                Console.WriteLine(
                    $"Previous count: {previousMessageCount} / Current Count: {currentRoomLog.MessageLog.Count}");

                Console.WriteLine($"Message count changed!");

                for (int i = previousMessageCount; i < currentRoomLog.MessageLog.Count; i++)
                {
                    ChatMessage message = currentRoomLog.MessageLog[i];
                    Console.WriteLine($"Sending message {message} to client...");
                    await responseStream.WriteAsync(message);
                }

                previousMessageCount = currentRoomLog.MessageLog.Count;
            }
        }

        public override async Task<SendReceipt> StreamMessagesToServer(
            IAsyncStreamReader<ChatMessage> requestStream,
            ServerCallContext context
        )
        {
            Console.WriteLine("Opening stream with client...");

            bool success = true;
            await foreach (ChatMessage message in requestStream.ReadAllAsync())
            {
                if (!_chatRooms.TryGetValue(message.ChatRoom.ChatRoomId, out ChatRoomLog value))
                {
                    Console.WriteLine($"Chat room {message.ChatRoom.ChatRoomId} not found!");
                    success = false;
                    break;
                }
                
                Console.WriteLine($"Adding message {message} to room...");
                value.MessageLog.Add(message);

                Console.WriteLine($"Received streaming message from client: {message}");
            }

            var sendReceipt = new SendReceipt { SentSuccessfully = success };
            Console.WriteLine($"Returning send receipt: {sendReceipt}");
            return sendReceipt;
        }

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
                    Console.WriteLine($"Received streaming message from client: {requestMessage}");

                    var timeMessage = new ChatMessage
                    {
                        TimeGeneratedEpochMillis = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds(),
                        Message = $"The mockingbird says: {requestMessage.Message}",
                        Nickname = "Time Server",
                        ClientLanguage = "C#",
                        ChatRoom = requestMessage.ChatRoom
                    };

                    Console.WriteLine($"Sending message {timeMessage} to client...");
                    await responseStream.WriteAsync(timeMessage);
                }
            }
        }
    }
}