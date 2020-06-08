package grpc.demo.java.moviecatalog;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The movie service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: movie-service.proto")
public final class MovieCatalogGrpc {

  private MovieCatalogGrpc() {}

  public static final String SERVICE_NAME = "moviecatalog.MovieCatalog";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem,
      grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> getSaveNewMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveNewMovie",
      requestType = grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem.class,
      responseType = grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem,
      grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> getSaveNewMovieMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem, grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> getSaveNewMovieMethod;
    if ((getSaveNewMovieMethod = MovieCatalogGrpc.getSaveNewMovieMethod) == null) {
      synchronized (MovieCatalogGrpc.class) {
        if ((getSaveNewMovieMethod = MovieCatalogGrpc.getSaveNewMovieMethod) == null) {
          MovieCatalogGrpc.getSaveNewMovieMethod = getSaveNewMovieMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem, grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveNewMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MovieCatalogMethodDescriptorSupplier("SaveNewMovie"))
              .build();
        }
      }
    }
    return getSaveNewMovieMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest,
      grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> getFetchExistingMovieMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FetchExistingMovie",
      requestType = grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest.class,
      responseType = grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest,
      grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> getFetchExistingMovieMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest, grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> getFetchExistingMovieMethod;
    if ((getFetchExistingMovieMethod = MovieCatalogGrpc.getFetchExistingMovieMethod) == null) {
      synchronized (MovieCatalogGrpc.class) {
        if ((getFetchExistingMovieMethod = MovieCatalogGrpc.getFetchExistingMovieMethod) == null) {
          MovieCatalogGrpc.getFetchExistingMovieMethod = getFetchExistingMovieMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest, grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FetchExistingMovie"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem.getDefaultInstance()))
              .setSchemaDescriptor(new MovieCatalogMethodDescriptorSupplier("FetchExistingMovie"))
              .build();
        }
      }
    }
    return getFetchExistingMovieMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MovieCatalogStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieCatalogStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieCatalogStub>() {
        @java.lang.Override
        public MovieCatalogStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieCatalogStub(channel, callOptions);
        }
      };
    return MovieCatalogStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MovieCatalogBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieCatalogBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieCatalogBlockingStub>() {
        @java.lang.Override
        public MovieCatalogBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieCatalogBlockingStub(channel, callOptions);
        }
      };
    return MovieCatalogBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MovieCatalogFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieCatalogFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieCatalogFutureStub>() {
        @java.lang.Override
        public MovieCatalogFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieCatalogFutureStub(channel, callOptions);
        }
      };
    return MovieCatalogFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The movie service definition.
   * </pre>
   */
  public static abstract class MovieCatalogImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Adds a movie to the repository
     * </pre>
     */
    public void saveNewMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem request,
        io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveNewMovieMethod(), responseObserver);
    }

    /**
     * <pre>
     *Retrieves a movie from the repository
     * </pre>
     */
    public void fetchExistingMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest request,
        io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> responseObserver) {
      asyncUnimplementedUnaryCall(getFetchExistingMovieMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveNewMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem,
                grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse>(
                  this, METHODID_SAVE_NEW_MOVIE)))
          .addMethod(
            getFetchExistingMovieMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest,
                grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem>(
                  this, METHODID_FETCH_EXISTING_MOVIE)))
          .build();
    }
  }

  /**
   * <pre>
   * The movie service definition.
   * </pre>
   */
  public static final class MovieCatalogStub extends io.grpc.stub.AbstractAsyncStub<MovieCatalogStub> {
    private MovieCatalogStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieCatalogStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieCatalogStub(channel, callOptions);
    }

    /**
     * <pre>
     * Adds a movie to the repository
     * </pre>
     */
    public void saveNewMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem request,
        io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveNewMovieMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Retrieves a movie from the repository
     * </pre>
     */
    public void fetchExistingMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest request,
        io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFetchExistingMovieMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The movie service definition.
   * </pre>
   */
  public static final class MovieCatalogBlockingStub extends io.grpc.stub.AbstractBlockingStub<MovieCatalogBlockingStub> {
    private MovieCatalogBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieCatalogBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieCatalogBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Adds a movie to the repository
     * </pre>
     */
    public grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse saveNewMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem request) {
      return blockingUnaryCall(
          getChannel(), getSaveNewMovieMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Retrieves a movie from the repository
     * </pre>
     */
    public grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem fetchExistingMovie(grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest request) {
      return blockingUnaryCall(
          getChannel(), getFetchExistingMovieMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The movie service definition.
   * </pre>
   */
  public static final class MovieCatalogFutureStub extends io.grpc.stub.AbstractFutureStub<MovieCatalogFutureStub> {
    private MovieCatalogFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieCatalogFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieCatalogFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Adds a movie to the repository
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse> saveNewMovie(
        grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveNewMovieMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Retrieves a movie from the repository
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem> fetchExistingMovie(
        grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFetchExistingMovieMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_NEW_MOVIE = 0;
  private static final int METHODID_FETCH_EXISTING_MOVIE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MovieCatalogImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MovieCatalogImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_NEW_MOVIE:
          serviceImpl.saveNewMovie((grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem) request,
              (io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.AddMovieResponse>) responseObserver);
          break;
        case METHODID_FETCH_EXISTING_MOVIE:
          serviceImpl.fetchExistingMovie((grpc.demo.java.moviecatalog.MovieCatalogProto.FetchMovieRequest) request,
              (io.grpc.stub.StreamObserver<grpc.demo.java.moviecatalog.MovieCatalogProto.MovieItem>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MovieCatalogBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MovieCatalogBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.demo.java.moviecatalog.MovieCatalogProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MovieCatalog");
    }
  }

  private static final class MovieCatalogFileDescriptorSupplier
      extends MovieCatalogBaseDescriptorSupplier {
    MovieCatalogFileDescriptorSupplier() {}
  }

  private static final class MovieCatalogMethodDescriptorSupplier
      extends MovieCatalogBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MovieCatalogMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MovieCatalogGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MovieCatalogFileDescriptorSupplier())
              .addMethod(getSaveNewMovieMethod())
              .addMethod(getFetchExistingMovieMethod())
              .build();
        }
      }
    }
    return result;
  }
}
