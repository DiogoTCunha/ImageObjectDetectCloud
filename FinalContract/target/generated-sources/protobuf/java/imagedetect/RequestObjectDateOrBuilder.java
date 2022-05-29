// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ImagesService.proto

package imagedetect;

public interface RequestObjectDateOrBuilder extends
    // @@protoc_insertion_point(interface_extends:imagesservice.RequestObjectDate)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string object = 1;</code>
   * @return The object.
   */
  java.lang.String getObject();
  /**
   * <code>string object = 1;</code>
   * @return The bytes for object.
   */
  com.google.protobuf.ByteString
      getObjectBytes();

  /**
   * <code>string initialDate = 2;</code>
   * @return The initialDate.
   */
  java.lang.String getInitialDate();
  /**
   * <code>string initialDate = 2;</code>
   * @return The bytes for initialDate.
   */
  com.google.protobuf.ByteString
      getInitialDateBytes();

  /**
   * <code>string endDate = 3;</code>
   * @return The endDate.
   */
  java.lang.String getEndDate();
  /**
   * <code>string endDate = 3;</code>
   * @return The bytes for endDate.
   */
  com.google.protobuf.ByteString
      getEndDateBytes();

  /**
   * <code>double certainty = 4;</code>
   * @return The certainty.
   */
  double getCertainty();
}