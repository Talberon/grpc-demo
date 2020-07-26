package grpc.demo.java.webchat.proto;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: webchat-service.proto")
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
  public static abstract class WebChatImplBase implements io.grpc.BindableService {

    /**
     */
    public void joinChatRoom(grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinChatRoomMethod(), responseObserver);
    }

    /**
     */
    public void sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinChatRoomMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.demo.java.webchat.proto.WebChatProto.ChatRoom,
                grpc.demo.java.webchat.proto.WebChatProto.ChatMessage>(
                  this, METHODID_JOIN_CHAT_ROOM)))
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.demo.java.webchat.proto.WebChatProto.ChatMessage,
                grpc.demo.java.webchat.proto.WebChatProto.SendReceipt>(
                  this, METHODID_SEND_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class WebChatStub extends io.grpc.stub.AbstractAsyncStub<WebChatStub> {
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
     */
    public void joinChatRoom(grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getJoinChatRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request,
        io.grpc.stub.StreamObserver<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WebChatBlockingStub extends io.grpc.stub.AbstractBlockingStub<WebChatBlockingStub> {
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
     */
    public java.util.Iterator<grpc.demo.java.webchat.proto.WebChatProto.ChatMessage> joinChatRoom(
        grpc.demo.java.webchat.proto.WebChatProto.ChatRoom request) {
      return blockingServerStreamingCall(
          getChannel(), getJoinChatRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.demo.java.webchat.proto.WebChatProto.SendReceipt sendMessage(grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WebChatFutureStub extends io.grpc.stub.AbstractFutureStub<WebChatFutureStub> {
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
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.demo.java.webchat.proto.WebChatProto.SendReceipt> sendMessage(
        grpc.demo.java.webchat.proto.WebChatProto.ChatMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN_CHAT_ROOM = 0;
  private static final int METHODID_SEND_MESSAGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WebChatImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WebChatImplBase serviceImpl, int methodId) {
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
        default:
          throw new AssertionError();
      }
    }
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
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
