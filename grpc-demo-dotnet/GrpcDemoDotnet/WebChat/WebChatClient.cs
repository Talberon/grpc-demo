using System;
using Grpc.Core;

namespace grpc_demo_dotnet.WebChat
{
    public class WebChatClient
    {
        public static void Run()
        {
            Console.WriteLine("Starting gRPC client...");

            var channel = new Channel("localhost", 8080, ChannelCredentials.Insecure);
            var client = new Webchat.WebChat.WebChatClient(channel);
            
            
        }
    }
}