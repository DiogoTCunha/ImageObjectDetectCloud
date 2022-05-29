// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ImagesService.proto

package imagedetect;

public final class ImagesService {
  private ImagesService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_Image_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_Image_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_ImageId_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_ImageId_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_ImageObjects_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_ImageObjects_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_Object_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_Object_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_RequestObjectDate_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_RequestObjectDate_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_imagesservice_ImageIds_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_imagesservice_ImageIds_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023ImagesService.proto\022\rimagesservice\"I\n\005" +
      "Image\022\027\n\017imageBlockBytes\030\001 \001(\014\022\020\n\010filena" +
      "me\030\002 \001(\t\022\025\n\rfileExtension\030\003 \001(\t\"\025\n\007Image" +
      "Id\022\n\n\002id\030\001 \001(\t\"6\n\014ImageObjects\022&\n\007object" +
      "s\030\001 \003(\0132\025.imagesservice.Object\"\'\n\006Object" +
      "\022\016\n\006object\030\001 \001(\t\022\r\n\005score\030\002 \001(\002\"\\\n\021Reque" +
      "stObjectDate\022\016\n\006object\030\001 \001(\t\022\023\n\013initialD" +
      "ate\030\002 \001(\t\022\017\n\007endDate\030\003 \001(\t\022\021\n\tcertainty\030" +
      "\004 \001(\001\"\027\n\010ImageIds\022\013\n\003ids\030\001 \003(\t2\232\002\n\rimage" +
      "Contract\022=\n\013uploadImage\022\024.imagesservice." +
      "Image\032\026.imagesservice.ImageId(\001\022A\n\ngetOb" +
      "jects\022\026.imagesservice.ImageId\032\033.imagesse" +
      "rvice.ImageObjects\022F\n\tgetImages\022 .images" +
      "service.RequestObjectDate\032\027.imagesservic" +
      "e.ImageIds\022?\n\rdownloadImage\022\026.imagesserv" +
      "ice.ImageId\032\024.imagesservice.Image0\001B\017\n\013i" +
      "magedetectP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_imagesservice_Image_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_imagesservice_Image_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_Image_descriptor,
        new java.lang.String[] { "ImageBlockBytes", "Filename", "FileExtension", });
    internal_static_imagesservice_ImageId_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_imagesservice_ImageId_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_ImageId_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_imagesservice_ImageObjects_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_imagesservice_ImageObjects_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_ImageObjects_descriptor,
        new java.lang.String[] { "Objects", });
    internal_static_imagesservice_Object_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_imagesservice_Object_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_Object_descriptor,
        new java.lang.String[] { "Object", "Score", });
    internal_static_imagesservice_RequestObjectDate_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_imagesservice_RequestObjectDate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_RequestObjectDate_descriptor,
        new java.lang.String[] { "Object", "InitialDate", "EndDate", "Certainty", });
    internal_static_imagesservice_ImageIds_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_imagesservice_ImageIds_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_imagesservice_ImageIds_descriptor,
        new java.lang.String[] { "Ids", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
