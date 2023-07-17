import grpc.demo.java.moviecatalog.proto.MovieCatalogGrpc
import grpc.demo.java.moviecatalog.proto.MovieCatalogProto
import io.grpc.ManagedChannelBuilder

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext() //No encryption
        .build()
    val client = MovieCatalogGrpc.newBlockingStub(channel) //Synchronous calls to the service
    val officeSpaceMovie =
        MovieCatalogProto.MovieItem.newBuilder().setName("Office Space").setPrice(10.99).setInStock(false).build()
    val saveResponse = client.saveNewMovie(officeSpaceMovie)
    println("Movie was saved: " + saveResponse.getWasSaved() + " / item id: " + saveResponse.getItemId())
    val fetchRequest = MovieCatalogProto.FetchMovieRequest.newBuilder().setName("Office Space").build()
    val fetchResponse = client.fetchExistingMovie(fetchRequest)
    println(
        java.lang.String.format(
            "Fetch movie %s at price $%f. In stock?: %s",
            fetchResponse.getName(),
            fetchResponse.getPrice(),
            fetchResponse.getInStock()
        )
    )
}