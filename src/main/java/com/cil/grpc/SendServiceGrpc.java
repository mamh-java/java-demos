package com.cil.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Trans service definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: push.proto")
public final class SendServiceGrpc {

  private SendServiceGrpc() {}

  public static final String SERVICE_NAME = "pb.SendService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<SendRequest,
          SendResponse> METHOD_SEND =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "pb.SendService", "Send"),
          io.grpc.protobuf.ProtoUtils.marshaller(SendRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(SendResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SendServiceStub newStub(io.grpc.Channel channel) {
    return new SendServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SendServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SendServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static SendServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SendServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Trans service definition
   * </pre>
   */
  public static abstract class SendServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void send(SendRequest request,
        io.grpc.stub.StreamObserver<SendResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEND, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEND,
            asyncUnaryCall(
              new MethodHandlers<
                      SendRequest,
                      SendResponse>(
                  this, METHODID_SEND)))
          .build();
    }
  }

  /**
   * <pre>
   * Trans service definition
   * </pre>
   */
  public static final class SendServiceStub extends io.grpc.stub.AbstractStub<SendServiceStub> {
    private SendServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SendServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SendServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SendServiceStub(channel, callOptions);
    }

    /**
     */
    public void send(SendRequest request,
        io.grpc.stub.StreamObserver<SendResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Trans service definition
   * </pre>
   */
  public static final class SendServiceBlockingStub extends io.grpc.stub.AbstractStub<SendServiceBlockingStub> {
    private SendServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SendServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SendServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SendServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public SendResponse send(SendRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEND, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Trans service definition
   * </pre>
   */
  public static final class SendServiceFutureStub extends io.grpc.stub.AbstractStub<SendServiceFutureStub> {
    private SendServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SendServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SendServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SendServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SendResponse> send(
        SendRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND, getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SendServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SendServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          serviceImpl.send((SendRequest) request,
              (io.grpc.stub.StreamObserver<SendResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class SendServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SendProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SendServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SendServiceDescriptorSupplier())
              .addMethod(METHOD_SEND)
              .build();
        }
      }
    }
    return result;
  }
}
