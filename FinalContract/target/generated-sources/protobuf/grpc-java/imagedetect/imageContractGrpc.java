package imagedetect;

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
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: ImagesService.proto")
public final class imageContractGrpc {

  private imageContractGrpc() {}

  public static final String SERVICE_NAME = "imagesservice.imageContract";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<imagedetect.Image,
      imagedetect.ImageId> getUploadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadImage",
      requestType = imagedetect.Image.class,
      responseType = imagedetect.ImageId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<imagedetect.Image,
      imagedetect.ImageId> getUploadImageMethod() {
    io.grpc.MethodDescriptor<imagedetect.Image, imagedetect.ImageId> getUploadImageMethod;
    if ((getUploadImageMethod = imageContractGrpc.getUploadImageMethod) == null) {
      synchronized (imageContractGrpc.class) {
        if ((getUploadImageMethod = imageContractGrpc.getUploadImageMethod) == null) {
          imageContractGrpc.getUploadImageMethod = getUploadImageMethod =
              io.grpc.MethodDescriptor.<imagedetect.Image, imagedetect.ImageId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.Image.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.ImageId.getDefaultInstance()))
              .setSchemaDescriptor(new imageContractMethodDescriptorSupplier("uploadImage"))
              .build();
        }
      }
    }
    return getUploadImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<imagedetect.ImageId,
      imagedetect.ImageObjects> getGetObjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getObjects",
      requestType = imagedetect.ImageId.class,
      responseType = imagedetect.ImageObjects.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<imagedetect.ImageId,
      imagedetect.ImageObjects> getGetObjectsMethod() {
    io.grpc.MethodDescriptor<imagedetect.ImageId, imagedetect.ImageObjects> getGetObjectsMethod;
    if ((getGetObjectsMethod = imageContractGrpc.getGetObjectsMethod) == null) {
      synchronized (imageContractGrpc.class) {
        if ((getGetObjectsMethod = imageContractGrpc.getGetObjectsMethod) == null) {
          imageContractGrpc.getGetObjectsMethod = getGetObjectsMethod =
              io.grpc.MethodDescriptor.<imagedetect.ImageId, imagedetect.ImageObjects>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getObjects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.ImageId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.ImageObjects.getDefaultInstance()))
              .setSchemaDescriptor(new imageContractMethodDescriptorSupplier("getObjects"))
              .build();
        }
      }
    }
    return getGetObjectsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<imagedetect.RequestObjectDate,
      imagedetect.ImageIds> getGetImagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getImages",
      requestType = imagedetect.RequestObjectDate.class,
      responseType = imagedetect.ImageIds.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<imagedetect.RequestObjectDate,
      imagedetect.ImageIds> getGetImagesMethod() {
    io.grpc.MethodDescriptor<imagedetect.RequestObjectDate, imagedetect.ImageIds> getGetImagesMethod;
    if ((getGetImagesMethod = imageContractGrpc.getGetImagesMethod) == null) {
      synchronized (imageContractGrpc.class) {
        if ((getGetImagesMethod = imageContractGrpc.getGetImagesMethod) == null) {
          imageContractGrpc.getGetImagesMethod = getGetImagesMethod =
              io.grpc.MethodDescriptor.<imagedetect.RequestObjectDate, imagedetect.ImageIds>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getImages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.RequestObjectDate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.ImageIds.getDefaultInstance()))
              .setSchemaDescriptor(new imageContractMethodDescriptorSupplier("getImages"))
              .build();
        }
      }
    }
    return getGetImagesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<imagedetect.ImageId,
      imagedetect.Image> getDownloadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "downloadImage",
      requestType = imagedetect.ImageId.class,
      responseType = imagedetect.Image.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<imagedetect.ImageId,
      imagedetect.Image> getDownloadImageMethod() {
    io.grpc.MethodDescriptor<imagedetect.ImageId, imagedetect.Image> getDownloadImageMethod;
    if ((getDownloadImageMethod = imageContractGrpc.getDownloadImageMethod) == null) {
      synchronized (imageContractGrpc.class) {
        if ((getDownloadImageMethod = imageContractGrpc.getDownloadImageMethod) == null) {
          imageContractGrpc.getDownloadImageMethod = getDownloadImageMethod =
              io.grpc.MethodDescriptor.<imagedetect.ImageId, imagedetect.Image>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "downloadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.ImageId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  imagedetect.Image.getDefaultInstance()))
              .setSchemaDescriptor(new imageContractMethodDescriptorSupplier("downloadImage"))
              .build();
        }
      }
    }
    return getDownloadImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static imageContractStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<imageContractStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<imageContractStub>() {
        @java.lang.Override
        public imageContractStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new imageContractStub(channel, callOptions);
        }
      };
    return imageContractStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static imageContractBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<imageContractBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<imageContractBlockingStub>() {
        @java.lang.Override
        public imageContractBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new imageContractBlockingStub(channel, callOptions);
        }
      };
    return imageContractBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static imageContractFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<imageContractFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<imageContractFutureStub>() {
        @java.lang.Override
        public imageContractFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new imageContractFutureStub(channel, callOptions);
        }
      };
    return imageContractFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class imageContractImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<imagedetect.Image> uploadImage(
        io.grpc.stub.StreamObserver<imagedetect.ImageId> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadImageMethod(), responseObserver);
    }

    /**
     */
    public void getObjects(imagedetect.ImageId request,
        io.grpc.stub.StreamObserver<imagedetect.ImageObjects> responseObserver) {
      asyncUnimplementedUnaryCall(getGetObjectsMethod(), responseObserver);
    }

    /**
     */
    public void getImages(imagedetect.RequestObjectDate request,
        io.grpc.stub.StreamObserver<imagedetect.ImageIds> responseObserver) {
      asyncUnimplementedUnaryCall(getGetImagesMethod(), responseObserver);
    }

    /**
     */
    public void downloadImage(imagedetect.ImageId request,
        io.grpc.stub.StreamObserver<imagedetect.Image> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadImageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadImageMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                imagedetect.Image,
                imagedetect.ImageId>(
                  this, METHODID_UPLOAD_IMAGE)))
          .addMethod(
            getGetObjectsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                imagedetect.ImageId,
                imagedetect.ImageObjects>(
                  this, METHODID_GET_OBJECTS)))
          .addMethod(
            getGetImagesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                imagedetect.RequestObjectDate,
                imagedetect.ImageIds>(
                  this, METHODID_GET_IMAGES)))
          .addMethod(
            getDownloadImageMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                imagedetect.ImageId,
                imagedetect.Image>(
                  this, METHODID_DOWNLOAD_IMAGE)))
          .build();
    }
  }

  /**
   */
  public static final class imageContractStub extends io.grpc.stub.AbstractAsyncStub<imageContractStub> {
    private imageContractStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected imageContractStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new imageContractStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<imagedetect.Image> uploadImage(
        io.grpc.stub.StreamObserver<imagedetect.ImageId> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void getObjects(imagedetect.ImageId request,
        io.grpc.stub.StreamObserver<imagedetect.ImageObjects> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetObjectsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getImages(imagedetect.RequestObjectDate request,
        io.grpc.stub.StreamObserver<imagedetect.ImageIds> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetImagesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downloadImage(imagedetect.ImageId request,
        io.grpc.stub.StreamObserver<imagedetect.Image> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDownloadImageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class imageContractBlockingStub extends io.grpc.stub.AbstractBlockingStub<imageContractBlockingStub> {
    private imageContractBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected imageContractBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new imageContractBlockingStub(channel, callOptions);
    }

    /**
     */
    public imagedetect.ImageObjects getObjects(imagedetect.ImageId request) {
      return blockingUnaryCall(
          getChannel(), getGetObjectsMethod(), getCallOptions(), request);
    }

    /**
     */
    public imagedetect.ImageIds getImages(imagedetect.RequestObjectDate request) {
      return blockingUnaryCall(
          getChannel(), getGetImagesMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<imagedetect.Image> downloadImage(
        imagedetect.ImageId request) {
      return blockingServerStreamingCall(
          getChannel(), getDownloadImageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class imageContractFutureStub extends io.grpc.stub.AbstractFutureStub<imageContractFutureStub> {
    private imageContractFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected imageContractFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new imageContractFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<imagedetect.ImageObjects> getObjects(
        imagedetect.ImageId request) {
      return futureUnaryCall(
          getChannel().newCall(getGetObjectsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<imagedetect.ImageIds> getImages(
        imagedetect.RequestObjectDate request) {
      return futureUnaryCall(
          getChannel().newCall(getGetImagesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_OBJECTS = 0;
  private static final int METHODID_GET_IMAGES = 1;
  private static final int METHODID_DOWNLOAD_IMAGE = 2;
  private static final int METHODID_UPLOAD_IMAGE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final imageContractImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(imageContractImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_OBJECTS:
          serviceImpl.getObjects((imagedetect.ImageId) request,
              (io.grpc.stub.StreamObserver<imagedetect.ImageObjects>) responseObserver);
          break;
        case METHODID_GET_IMAGES:
          serviceImpl.getImages((imagedetect.RequestObjectDate) request,
              (io.grpc.stub.StreamObserver<imagedetect.ImageIds>) responseObserver);
          break;
        case METHODID_DOWNLOAD_IMAGE:
          serviceImpl.downloadImage((imagedetect.ImageId) request,
              (io.grpc.stub.StreamObserver<imagedetect.Image>) responseObserver);
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
        case METHODID_UPLOAD_IMAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadImage(
              (io.grpc.stub.StreamObserver<imagedetect.ImageId>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class imageContractBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    imageContractBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return imagedetect.ImagesService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("imageContract");
    }
  }

  private static final class imageContractFileDescriptorSupplier
      extends imageContractBaseDescriptorSupplier {
    imageContractFileDescriptorSupplier() {}
  }

  private static final class imageContractMethodDescriptorSupplier
      extends imageContractBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    imageContractMethodDescriptorSupplier(String methodName) {
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
      synchronized (imageContractGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new imageContractFileDescriptorSupplier())
              .addMethod(getUploadImageMethod())
              .addMethod(getGetObjectsMethod())
              .addMethod(getGetImagesMethod())
              .addMethod(getDownloadImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
