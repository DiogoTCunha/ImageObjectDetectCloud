// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ImagesService.proto

package imagedetect;

/**
 * Protobuf type {@code imagesservice.Object}
 */
public  final class Object extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:imagesservice.Object)
    ObjectOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Object.newBuilder() to construct.
  private Object(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Object() {
    object_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Object();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Object(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            object_ = s;
            break;
          }
          case 21: {

            score_ = input.readFloat();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return imagedetect.ImagesService.internal_static_imagesservice_Object_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return imagedetect.ImagesService.internal_static_imagesservice_Object_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            imagedetect.Object.class, imagedetect.Object.Builder.class);
  }

  public static final int OBJECT_FIELD_NUMBER = 1;
  private volatile java.lang.Object object_;
  /**
   * <code>string object = 1;</code>
   * @return The object.
   */
  public java.lang.String getObject() {
    java.lang.Object ref = object_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      object_ = s;
      return s;
    }
  }
  /**
   * <code>string object = 1;</code>
   * @return The bytes for object.
   */
  public com.google.protobuf.ByteString
      getObjectBytes() {
    java.lang.Object ref = object_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      object_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SCORE_FIELD_NUMBER = 2;
  private float score_;
  /**
   * <code>float score = 2;</code>
   * @return The score.
   */
  public float getScore() {
    return score_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getObjectBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, object_);
    }
    if (score_ != 0F) {
      output.writeFloat(2, score_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getObjectBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, object_);
    }
    if (score_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, score_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof imagedetect.Object)) {
      return super.equals(obj);
    }
    imagedetect.Object other = (imagedetect.Object) obj;

    if (!getObject()
        .equals(other.getObject())) return false;
    if (java.lang.Float.floatToIntBits(getScore())
        != java.lang.Float.floatToIntBits(
            other.getScore())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + OBJECT_FIELD_NUMBER;
    hash = (53 * hash) + getObject().hashCode();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getScore());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static imagedetect.Object parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static imagedetect.Object parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static imagedetect.Object parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static imagedetect.Object parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static imagedetect.Object parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static imagedetect.Object parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static imagedetect.Object parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static imagedetect.Object parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static imagedetect.Object parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static imagedetect.Object parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static imagedetect.Object parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static imagedetect.Object parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(imagedetect.Object prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code imagesservice.Object}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:imagesservice.Object)
      imagedetect.ObjectOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return imagedetect.ImagesService.internal_static_imagesservice_Object_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return imagedetect.ImagesService.internal_static_imagesservice_Object_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              imagedetect.Object.class, imagedetect.Object.Builder.class);
    }

    // Construct using imagedetect.Object.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      object_ = "";

      score_ = 0F;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return imagedetect.ImagesService.internal_static_imagesservice_Object_descriptor;
    }

    @java.lang.Override
    public imagedetect.Object getDefaultInstanceForType() {
      return imagedetect.Object.getDefaultInstance();
    }

    @java.lang.Override
    public imagedetect.Object build() {
      imagedetect.Object result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public imagedetect.Object buildPartial() {
      imagedetect.Object result = new imagedetect.Object(this);
      result.object_ = object_;
      result.score_ = score_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof imagedetect.Object) {
        return mergeFrom((imagedetect.Object)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(imagedetect.Object other) {
      if (other == imagedetect.Object.getDefaultInstance()) return this;
      if (!other.getObject().isEmpty()) {
        object_ = other.object_;
        onChanged();
      }
      if (other.getScore() != 0F) {
        setScore(other.getScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      imagedetect.Object parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (imagedetect.Object) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object object_ = "";
    /**
     * <code>string object = 1;</code>
     * @return The object.
     */
    public java.lang.String getObject() {
      java.lang.Object ref = object_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        object_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string object = 1;</code>
     * @return The bytes for object.
     */
    public com.google.protobuf.ByteString
        getObjectBytes() {
      java.lang.Object ref = object_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        object_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string object = 1;</code>
     * @param value The object to set.
     * @return This builder for chaining.
     */
    public Builder setObject(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      object_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string object = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearObject() {
      
      object_ = getDefaultInstance().getObject();
      onChanged();
      return this;
    }
    /**
     * <code>string object = 1;</code>
     * @param value The bytes for object to set.
     * @return This builder for chaining.
     */
    public Builder setObjectBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      object_ = value;
      onChanged();
      return this;
    }

    private float score_ ;
    /**
     * <code>float score = 2;</code>
     * @return The score.
     */
    public float getScore() {
      return score_;
    }
    /**
     * <code>float score = 2;</code>
     * @param value The score to set.
     * @return This builder for chaining.
     */
    public Builder setScore(float value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float score = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearScore() {
      
      score_ = 0F;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:imagesservice.Object)
  }

  // @@protoc_insertion_point(class_scope:imagesservice.Object)
  private static final imagedetect.Object DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new imagedetect.Object();
  }

  public static imagedetect.Object getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Object>
      PARSER = new com.google.protobuf.AbstractParser<Object>() {
    @java.lang.Override
    public Object parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Object(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Object> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Object> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public imagedetect.Object getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

