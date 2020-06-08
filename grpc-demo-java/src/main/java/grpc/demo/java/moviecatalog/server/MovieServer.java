package grpc.demo.java.moviecatalog.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MovieServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    var service = new MovieCatalogService();

    System.out.println("Starting movie server...");
    final int port = 8080;
    var server = ServerBuilder.forPort(port).addService(service).build().start();
    System.out.println("Server started on port " + port + "!");

    server.awaitTermination();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      // Use stderr here since the logger may have been reset by its JVM shutdown hook.
      System.err.println("*** shutting down gRPC server since JVM is shutting down");
      try {
        stopServer(server);
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
      System.err.println("*** server shut down");
    }));

  }

  private static void stopServer(Server server) throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }
}
