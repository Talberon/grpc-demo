using System;
using Webchat;

namespace grpc_demo_dotnet.WebChat.Util
{
    public class ChatRoomNotFoundException : Exception
    {
        public ChatRoomNotFoundException(ChatRoom chatRoom) :
            base($"Chat room {chatRoom.ChatRoomId} not found!")
        {
        }
    }
}