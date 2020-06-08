package grpc.demo.java.moviecatalog.server;

public class MovieNotFoundException extends Exception {

  public MovieNotFoundException(String movieName) {
    super("Could not find movie with name: " + movieName);
  }
}
