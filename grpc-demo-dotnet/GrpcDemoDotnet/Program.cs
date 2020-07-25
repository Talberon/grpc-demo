using grpc_demo_dotnet.WebChat;

namespace grpc_demo_dotnet
{
    internal static class Program
    {
        private static void Main(string[] args)
        {
            WebChatServer.Run();
            
            // WebChatClient.Run();
        }
    }
}