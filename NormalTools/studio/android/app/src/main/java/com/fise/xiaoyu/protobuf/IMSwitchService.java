// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: IM.SwitchService.proto

package com.fise.xiaoyu.protobuf;

public final class IMSwitchService {
  private IMSwitchService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface IMP2PCmdMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:IM.SwitchService.IMP2PCmdMsg)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>required uint32 from_user_id = 1;</code>
     *
     * <pre>
     *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
     * </pre>
     */
    boolean hasFromUserId();
    /**
     * <code>required uint32 from_user_id = 1;</code>
     *
     * <pre>
     *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
     * </pre>
     */
    int getFromUserId();

    /**
     * <code>required uint32 to_user_id = 2;</code>
     */
    boolean hasToUserId();
    /**
     * <code>required uint32 to_user_id = 2;</code>
     */
    int getToUserId();

    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    boolean hasCmdMsgData();
    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    java.lang.String getCmdMsgData();
    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    com.google.protobuf.ByteString
        getCmdMsgDataBytes();
  }
  /**
   * Protobuf type {@code IM.SwitchService.IMP2PCmdMsg}
   */
  public static final class IMP2PCmdMsg extends
      com.google.protobuf.GeneratedMessageLite implements
      // @@protoc_insertion_point(message_implements:IM.SwitchService.IMP2PCmdMsg)
      IMP2PCmdMsgOrBuilder {
    // Use IMP2PCmdMsg.newBuilder() to construct.
    private IMP2PCmdMsg(com.google.protobuf.GeneratedMessageLite.Builder builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private IMP2PCmdMsg(boolean noInit) { this.unknownFields = com.google.protobuf.ByteString.EMPTY;}

    private static final IMP2PCmdMsg defaultInstance;
    public static IMP2PCmdMsg getDefaultInstance() {
      return defaultInstance;
    }

    public IMP2PCmdMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.ByteString unknownFields;
    private IMP2PCmdMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.ByteString.Output unknownFieldsOutput =
          com.google.protobuf.ByteString.newOutput();
      com.google.protobuf.CodedOutputStream unknownFieldsCodedOutput =
          com.google.protobuf.CodedOutputStream.newInstance(
              unknownFieldsOutput);
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFieldsCodedOutput,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              fromUserId_ = input.readUInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              toUserId_ = input.readUInt32();
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              cmdMsgData_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        try {
          unknownFieldsCodedOutput.flush();
        } catch (java.io.IOException e) {
        // Should not happen
        } finally {
          unknownFields = unknownFieldsOutput.toByteString();
        }
        makeExtensionsImmutable();
      }
    }
    public static com.google.protobuf.Parser<IMP2PCmdMsg> PARSER =
        new com.google.protobuf.AbstractParser<IMP2PCmdMsg>() {
      public IMP2PCmdMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new IMP2PCmdMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<IMP2PCmdMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int FROM_USER_ID_FIELD_NUMBER = 1;
    private int fromUserId_;
    /**
     * <code>required uint32 from_user_id = 1;</code>
     *
     * <pre>
     *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
     * </pre>
     */
    public boolean hasFromUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint32 from_user_id = 1;</code>
     *
     * <pre>
     *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
     * </pre>
     */
    public int getFromUserId() {
      return fromUserId_;
    }

    public static final int TO_USER_ID_FIELD_NUMBER = 2;
    private int toUserId_;
    /**
     * <code>required uint32 to_user_id = 2;</code>
     */
    public boolean hasToUserId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required uint32 to_user_id = 2;</code>
     */
    public int getToUserId() {
      return toUserId_;
    }

    public static final int CMD_MSG_DATA_FIELD_NUMBER = 3;
    private java.lang.Object cmdMsgData_;
    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    public boolean hasCmdMsgData() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    public java.lang.String getCmdMsgData() {
      java.lang.Object ref = cmdMsgData_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          cmdMsgData_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string cmd_msg_data = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCmdMsgDataBytes() {
      java.lang.Object ref = cmdMsgData_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cmdMsgData_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      fromUserId_ = 0;
      toUserId_ = 0;
      cmdMsgData_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasFromUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasToUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCmdMsgData()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt32(1, fromUserId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, toUserId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getCmdMsgDataBytes());
      }
      output.writeRawBytes(unknownFields);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, fromUserId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, toUserId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getCmdMsgDataBytes());
      }
      size += unknownFields.size();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    /**
     * Protobuf type {@code IM.SwitchService.IMP2PCmdMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg, Builder>
        implements
        // @@protoc_insertion_point(builder_implements:IM.SwitchService.IMP2PCmdMsg)
        com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsgOrBuilder {
      // Construct using com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        fromUserId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        toUserId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        cmdMsgData_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg getDefaultInstanceForType() {
        return com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg.getDefaultInstance();
      }

      public com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg build() {
        com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg buildPartial() {
        com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg result = new com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.fromUserId_ = fromUserId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.toUserId_ = toUserId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.cmdMsgData_ = cmdMsgData_;
        result.bitField0_ = to_bitField0_;
        return result;
      }

      public Builder mergeFrom(com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg other) {
        if (other == com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg.getDefaultInstance()) return this;
        if (other.hasFromUserId()) {
          setFromUserId(other.getFromUserId());
        }
        if (other.hasToUserId()) {
          setToUserId(other.getToUserId());
        }
        if (other.hasCmdMsgData()) {
          bitField0_ |= 0x00000004;
          cmdMsgData_ = other.cmdMsgData_;
          
        }
        setUnknownFields(
            getUnknownFields().concat(other.unknownFields));
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFromUserId()) {
          
          return false;
        }
        if (!hasToUserId()) {
          
          return false;
        }
        if (!hasCmdMsgData()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.fise.xiaoyu.protobuf.IMSwitchService.IMP2PCmdMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int fromUserId_ ;
      /**
       * <code>required uint32 from_user_id = 1;</code>
       *
       * <pre>
       *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
       * </pre>
       */
      public boolean hasFromUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required uint32 from_user_id = 1;</code>
       *
       * <pre>
       *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
       * </pre>
       */
      public int getFromUserId() {
        return fromUserId_;
      }
      /**
       * <code>required uint32 from_user_id = 1;</code>
       *
       * <pre>
       *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
       * </pre>
       */
      public Builder setFromUserId(int value) {
        bitField0_ |= 0x00000001;
        fromUserId_ = value;
        
        return this;
      }
      /**
       * <code>required uint32 from_user_id = 1;</code>
       *
       * <pre>
       *cmd id:CID_SWITCH_P2P_CMD = 0x0601;
       * </pre>
       */
      public Builder clearFromUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        fromUserId_ = 0;
        
        return this;
      }

      private int toUserId_ ;
      /**
       * <code>required uint32 to_user_id = 2;</code>
       */
      public boolean hasToUserId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required uint32 to_user_id = 2;</code>
       */
      public int getToUserId() {
        return toUserId_;
      }
      /**
       * <code>required uint32 to_user_id = 2;</code>
       */
      public Builder setToUserId(int value) {
        bitField0_ |= 0x00000002;
        toUserId_ = value;
        
        return this;
      }
      /**
       * <code>required uint32 to_user_id = 2;</code>
       */
      public Builder clearToUserId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        toUserId_ = 0;
        
        return this;
      }

      private java.lang.Object cmdMsgData_ = "";
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public boolean hasCmdMsgData() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public java.lang.String getCmdMsgData() {
        java.lang.Object ref = cmdMsgData_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            cmdMsgData_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public com.google.protobuf.ByteString
          getCmdMsgDataBytes() {
        java.lang.Object ref = cmdMsgData_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          cmdMsgData_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public Builder setCmdMsgData(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        cmdMsgData_ = value;
        
        return this;
      }
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public Builder clearCmdMsgData() {
        bitField0_ = (bitField0_ & ~0x00000004);
        cmdMsgData_ = getDefaultInstance().getCmdMsgData();
        
        return this;
      }
      /**
       * <code>required string cmd_msg_data = 3;</code>
       */
      public Builder setCmdMsgDataBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        cmdMsgData_ = value;
        
        return this;
      }

      // @@protoc_insertion_point(builder_scope:IM.SwitchService.IMP2PCmdMsg)
    }

    static {
      defaultInstance = new IMP2PCmdMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:IM.SwitchService.IMP2PCmdMsg)
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
