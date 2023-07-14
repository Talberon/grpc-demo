package grpc.demo.java.webchat.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.1)",
    comments = "Source: webchat-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WebChatGrpc {

  private WebChatGrpc() {}

  public static final String SERVICE_NAME = "webchat.WebChat";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatRoom,
      grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinChatRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinChatRoom",
      requestType = grpc.demo.java.webchat.proto.WebChatProto.ChatRoom.class,
      responseType = grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatRoom,
      grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinChatRoomMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatRoom, grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinChatRoomMethod;
    if ((getJoinChatRoomMethod = WebChatGrpc.getJoinChatRoomMethod) == null) {
      synchronized (WebChatGrpc.class) {
        if ((getJoinChatRoomMethod = WebChatGrpc.getJoinChatRoomMethod) == null) {
          WebChatGrpc.getJoinChatRoomMethod = getJoinChatRoomMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.webchat.proto.WebChatProto.ChatRoom, grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinChatRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatRoom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new WebChatMethodDescriptorSupplier("JoinChatRoom"))
              .build();
        }
      }
    }
    return getJoinChatRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getStreamMessagesToServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamMessagesToServer",
      requestType = grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.class,
      responseType = grpc.demo.java.webchat.proto.WebChatProto.SendReceipt.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getStreamMessagesToServerMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getStreamMessagesToServerMethod;
    if ((getStreamMessagesToServerMethod = WebChatGrpc.getStreamMessagesToServerMethod) == null) {
      synchronized (WebChatGrpc.class) {
        if ((getStreamMessagesToServerMethod = WebChatGrpc.getStreamMessagesToServerMethod) == null) {
          WebChatGrpc.getStreamMessagesToServerMethod = getStreamMessagesToServerMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamMessagesToServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.SendReceipt.getDefaultInstance()))
              .setSchemaDescriptor(new WebChatMethodDescriptorSupplier("StreamMessagesToServer"))
              .build();
        }
      }
    }
    return getStreamMessagesToServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinStreamSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinStreamSession",
      requestType = grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.class,
      responseType = grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinStreamSessionMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> getJoinStreamSessionMethod;
    if ((getJoinStreamSessionMethod = WebChatGrpc.getJoinStreamSessionMethod) == null) {
      synchronized (WebChatGrpc.class) {
        if ((getJoinStreamSessionMethod = WebChatGrpc.getJoinStreamSessionMethod) == null) {
          WebChatGrpc.getJoinStreamSessionMethod = getJoinStreamSessionMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinStreamSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new WebChatMethodDescriptorSupplier("JoinStreamSession"))
              .build();
        }
      }
    }
    return getJoinStreamSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.class,
      responseType = grpc.demo.java.webchat.proto.WebChatProto.SendReceipt.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
      grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getSendMessageMethod() {
    io.grpc.MethodDescriptor<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> getSendMessageMethod;
    if ((getSendMessageMethod = WebChatGrpc.getSendMessageMethod) == null) {
      synchronized (WebChatGrpc.class) {
        if ((getSendMessageMethod = WebChatGrpc.getSendMessageMethod) == null) {
          WebChatGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage, grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.demo.java.webchat.proto.WebChatProto.SendReceipt.getDefaultInstance()))
              .setSchemaDescriptor(new WebChatMethodDescriptorSupplier("SendMessage"))
              .build();
        }
      }
    }
    return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WebChatStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WebChatStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WebChatStub>() {
        @java.lang.Override
        public WebChatStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WebChatStub(channel, callOptions);
        }
      };
    return WebChatStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WebChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WebChatBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WebChatBlockingStub>() {
        @java.lang.Override
        public WebChatBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WebChatBlockingStub(channel, callOptions);
        }
      };
    return WebChatBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WebChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WebChatFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WebChatFutureStub>() {
        @java.lang.Override
        public WebChatFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WebChatFutureStub(channel, callOptions);
        }
      };
    return WebChatFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    default void joinChatRoom(grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinChatRoomMethod(), responseObserver);
    }

    /**
     * <pre>
     *Client-side Streaming
     * </pre>
     */
    default io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> streamMessagesToServer(
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamMessagesToServerMethod(), responseObserver);
    }

    /**
     * <pre>
     *Bidirectional Streaming
     * </pre>
     */
    default io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> joinStreamSession(
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getJoinStreamSessionMethod(), responseObserver);
    }

    /**
     * <pre>
     *Unary Call
     * </pre>
     */
    default void sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WebChat.
   */
  public static abstract class WebChatImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WebChatGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WebChat.
   */
  public static final class WebChatStub
      extends io.grpc.stub.AbstractAsyncStub<WebChatStub> {
    private WebChatStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebChatStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WebChatStub(channel, callOptions);
    }

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    public void joinChatRoom(grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getJoinChatRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Client-side Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> streamMessagesToServer(
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getStreamMessagesToServerMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *Bidirectional Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> joinStreamSession(
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getJoinStreamSessionMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *Unary Call
     * </pre>
     */
    public void sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WebChat.
   */
  public static final class WebChatBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WebChatBlockingStub> {
    private WebChatBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebChatBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WebChatBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    public java.util.Iterator<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> joinChatRoom(
        grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getJoinChatRoomMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Unary Call
     * </pre>
     */
    public grpc.demo.java.webchat.proto.WebChatProto.SendReceipt sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WebChat.
   */
  public static final class WebChatFutureStub
      extends io.grpc.stub.AbstractFutureStub<WebChatFutureStub> {
    private WebChatFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WebChatFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WebChatFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary Call
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> sendMessage(
        grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_CHAT_ROOM = 0;
  private static final int METHODID_SEND_MESSAGE = 1;
  private static final int METHODID_STREAM_MESSAGES_TO_SERVER = 2;
  private static final int METHODID_JOIN_STREAM_SESSION = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN_CHAT_ROOM:
          serviceImpl.joinChatRoom((grpc.demo.java.webchat.proto.WebChatProto.ChatRoom) request,
              (io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((grpc.demo.java.webchat.proto.WebChatProto.ChatMessage) request,
              (io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>) responseObserver);
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
        case METHODID_STREAM_MESSAGES_TO_SERVER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamMessagesToServer(
              (io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>) responseObserver);
        case METHODID_JOIN_STREAM_SESSION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.joinStreamSession(
              (io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getJoinChatRoomMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              grpc.demo.java.webchat.proto.WebChatProto.ChatRoom,
              grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>(
                service, METHODID_JOIN_CHAT_ROOM)))
        .addMethod(
          getStreamMessagesToServerMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
              grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>(
                service, METHODID_STREAM_MESSAGES_TO_SERVER)))
        .addMethod(
          getJoinStreamSessionMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
              grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>(
                service, METHODID_JOIN_STREAM_SESSION)))
        .addMethod(
          getSendMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
              grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>(
                service, METHODID_SEND_MESSAGE)))
        .build();
  }

  private static abstract class WebChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WebChatBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.demo.java.webchat.proto.WebChatProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WebChat");
    }
  }

  private static final class WebChatFileDescriptorSupplier
      extends WebChatBaseDescriptorSupplier {
    WebChatFileDescriptorSupplier() {}
  }

  private static final class WebChatMethodDescriptorSupplier
      extends WebChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WebChatMethodDescriptorSupplier(String methodName) {
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
      synchronized (WebChatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WebChatFileDescriptorSupplier())
              .addMethod(getJoinChatRoomMethod())
              .addMethod(getStreamMessagesToServerMethod())
              .addMethod(getJoinStreamSessionMethod())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
