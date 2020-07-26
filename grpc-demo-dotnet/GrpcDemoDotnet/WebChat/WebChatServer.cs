using System;
using Grpc.Core;

namespace grpc_demo_dotnet.WebChat
{
    public static class WebChatServer
    {
        public static void Run()
        {
            Console.WriteLine("Starting gRPC client...");
            const int port = 9090;

            var server = new Server
            {
                Services = {Webchat.WebChat.BindService(new WebChatServiceImpl())},
                Ports = {new ServerPort("localhost", port, ServerCredentials.Insecure)}
            };

            server.Start();

            Console.WriteLine($"Started chat server on port {port}");
            Console.WriteLine("Press any key to stop the server...");
            Console.ReadKey();
            server.ShutdownAsync().Wait();
        }
    }
}