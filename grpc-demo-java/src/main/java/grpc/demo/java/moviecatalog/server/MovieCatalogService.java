package grpc.demo.java.moviecatalog.server;

import static io.grpc.Status.ALREADY_EXISTS;
import static io.grpc.Status.NOT_FOUND;

import grpc.demo.java.moviecatalog.MovieCatalogGrpc;
import grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse;
import grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest;
import grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

public class MovieCatalogService extends MovieCatalogGrpc.MovieCatalogImplBase {

  private final List<MovieItem> moviesInCatalog;

  public MovieCatalogService() {
    var schindlersList = MovieItem.newBuilder().setName("Schindler's List").setPrice(12.99d).setInStock(true).build();
    var piratesOfSiliconValley = MovieItem.newBuilder().setName("The Pirates of Silicon Valley").setPrice(10.99d)
        .setInStock(true).build();
    var warGames = MovieItem.newBuilder().setName("War Games").setPrice(22.99d).setInStock(true).build();
    var princessBride = MovieItem.newBuilder().setName("The Princess Bride").setPrice(15.99d).setInStock(true).build();
    var lionKing = MovieItem.newBuilder().setName("The Lion King").setPrice(24.99d).setInStock(true).build();
    var aKnightsTale = MovieItem.newBuilder().setName("A Knight's Tale").setPrice(8.99d).setInStock(true).build();

    moviesInCatalog = new ArrayList<>();
    moviesInCatalog.add(schindlersList);
    moviesInCatalog.add(piratesOfSiliconValley);
    moviesInCatalog.add(warGames);
    moviesInCatalog.add(princessBride);
    moviesInCatalog.add(lionKing);
    moviesInCatalog.add(aKnightsTale);
  }

  @Override
  public void fetchExistingMovie(FetchMovieRequest request, StreamObserver<MovieItem> responseObserver) {
    MovieItem fetchedMovie;

    try {
      fetchedMovie = moviesInCatalog.stream()
          .filter(movie -> movie.getName().contains(request.getName()))
          .findFirst()
          .orElseThrow(() -> new MovieNotFoundException(request.getName()));

    } catch (MovieNotFoundException e) {
      e.printStackTrace();
      //Throw an exception visible to the client
      responseObserver.onError(new StatusException(NOT_FOUND));
      return;
    }

    System.out.println("Found movie: " + fetchedMovie.getName());
    //Send response
    responseObserver.onNext(fetchedMovie);
    //Close request gracefully
    responseObserver.onCompleted();
  }

  @Override
  public void saveNewMovie(MovieItem request, StreamObserver<AddMovieResponse> responseObserver) {

    boolean movieAlreadyExists = moviesInCatalog.stream()
        .anyMatch(movie -> movie.getName().equalsIgnoreCase(request.getName()));

    if (movieAlreadyExists) {
      System.err.println("Movie " + request.getName() + " already exists!");
      responseObserver.onError(new StatusException(ALREADY_EXISTS));
    } else {

      moviesInCatalog.add(request);
      System.out.println("Saved movie: " + request.getName());

      var response = AddMovieResponse.newBuilder()
          .setWasSaved(true)
          .setItemId(moviesInCatalog.indexOf(request))
          .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    }
  }
}
