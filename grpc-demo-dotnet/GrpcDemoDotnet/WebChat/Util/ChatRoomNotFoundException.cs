using System;
using Webchat;

namespace GrpcDemoDotnet.WebChat.Util
{
    public class ChatRoomNotFoundException : Exception
    {
        public ChatRoomNotFoundException(ChatRoom chatRoom) :
            base($"Chat room {chatRoom.ChatRoomId} not found!")
        {
        }
    }
}