package grpc.demo.java.moviecatalog.client;

import grpc.demo.java.moviecatalog.proto.MovieCatalogGrpc;
import grpc.demo.java.moviecatalog.proto.MovieCatalogProto.FetchMovieRequest;
import grpc.demo.java.moviecatalog.proto.MovieCatalogProto.MovieItem;
import io.grpc.ManagedChannelBuilder;

public class MovieClient {

  public static void main(String[] args) {
    var channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext() //No encryption
        .build();

    var client = MovieCatalogGrpc.newBlockingStub(channel); //Synchronous calls to the service

    var theMatrix = MovieItem.newBuilder().setName("The Matrix").setPrice(12.99).setInStock(true).build();

    var saveResponse = client.saveNewMovie(theMatrix);
    System.out.println("Movie was saved: " + saveResponse.getWasSaved() + " / item id: " + saveResponse.getItemId());

    var fetchRequest = FetchMovieRequest.newBuilder().setName("The Matrix").build();
    var fetchResponse = client.fetchExistingMovie(fetchRequest);

    System.out.println(String.format(
        "Fetch movie %s at price $%f. In stock?: %s",
        fetchResponse.getName(),
        fetchResponse.getPrice(),
        fetchResponse.getInStock()
    ));
  }
}
