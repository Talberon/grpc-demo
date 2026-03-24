using System.Threading.Tasks;
using GrpcDemoDotnet.WebChat;

namespace GrpcDemoDotnet
{
    internal static class Program
    {
        private static async Task Main(string[] args)
        {
            if (args[0] == "server") WebChatServer.Run();
            if (args[0] == "client") await WebChatClient.Run();
        }
    }
}