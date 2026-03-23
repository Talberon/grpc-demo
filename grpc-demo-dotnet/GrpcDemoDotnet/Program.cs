using GrpcDemoDotnet.WebChat;

namespace GrpcDemoDotnet
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