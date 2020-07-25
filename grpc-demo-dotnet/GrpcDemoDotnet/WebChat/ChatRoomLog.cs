using System.Collections.Generic;
using Webchat;

namespace grpc_demo_dotnet.WebChat
{
    public class ChatRoomLog
    {
        public List<ChatMessage> MessageLog { get; }

        public ChatRoomLog(List<ChatMessage> messageLog)
        {
            MessageLog = messageLog;
        }
    }
}