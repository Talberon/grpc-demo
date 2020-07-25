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
        private readonly Dictionary<string, ChatRoomLog> _chatRooms;

        public WebChatServiceImpl()
        {
            _chatRooms = new Dictionary<string, ChatRoomLog>();
        }

        public override Task<SendReceipt> SendMessage(ChatMessage message, ServerCallContext context)
        {
            if (!_chatRooms.ContainsKey(message.ChatRoom.ChatRoomId))
            {
                Console.WriteLine($"Chat room {message.ChatRoom.ChatRoomId} not found!");
                return Task.FromResult(new SendReceipt()
                {
                    SentSuccessfully = false
                });
            }

            Console.WriteLine($"Adding message {message} to room...");
            _chatRooms[message.ChatRoom.ChatRoomId].MessageLog.Add(message);

            return Task.FromResult(new SendReceipt()
            {
                SentSuccessfully = true
            });
        }

        public override async Task JoinChatRoom(ChatRoom chatRoom, IServerStreamWriter<ChatMessage> responseStream,
            ServerCallContext context)
        {
            ChatRoomLog currentRoomLog = _chatRooms.ContainsKey(chatRoom.ChatRoomId)
                ? _chatRooms[chatRoom.ChatRoomId]
                : _chatRooms[chatRoom.ChatRoomId] = new ChatRoomLog(new List<ChatMessage>());

            Console.WriteLine($"Joining chat room '{chatRoom.ChatRoomId}'...");

            int previousMessageCount = currentRoomLog.MessageLog.Count;

            while (!context.CancellationToken.IsCancellationRequested)
            {
                Thread.Sleep(HeartbeatInterval);

                //TODO Handle if messages are edited
                int messageChange = previousMessageCount - currentRoomLog.MessageLog.Count;
                
                Console.WriteLine($"Previous count: {previousMessageCount} / Current Count: {currentRoomLog.MessageLog.Count}");

                if (messageChange == 0) continue;
                
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
    }
}