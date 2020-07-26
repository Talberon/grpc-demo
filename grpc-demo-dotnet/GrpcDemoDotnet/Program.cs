using grpc_demo_dotnet.WebChat;

namespace grpc_demo_dotnet
{
    internal static class Program
    {
        private static void Main(string[] args)
        {
            if (args[0] == "server") WebChatServer.Run();
            if (args[0] == "client") WebChatClient.Run();
        }
    }
}