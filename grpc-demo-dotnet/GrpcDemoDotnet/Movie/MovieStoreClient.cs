using System;
using Grpc.Core;
using Moviecatalog;

namespace grpc_demo_dotnet.Movie
{
    public class MovieStoreClient
    {
        public static void Run()
        {
            Console.WriteLine("Starting gRPC client...");

            var channel = new Channel("localhost", 8080, ChannelCredentials.Insecure);
            var client = new MovieCatalog.MovieCatalogClient(channel);

            var fetchRequest = new FetchMovieRequest
            {
                Name = "War Games"
            };

            MovieItem response = client.FetchExistingMovie(fetchRequest);
            Console.WriteLine($"Fetched movie from server: {response}");

            var createRequest = new MovieItem
            {
                Name = "Toy Story",
                Price = 29.99d,
                InStock = false
            };

            AddMovieResponse saveResponse = client.SaveNewMovie(createRequest);
            Console.WriteLine($"Saved new movie: {saveResponse}");
        }
    }
}