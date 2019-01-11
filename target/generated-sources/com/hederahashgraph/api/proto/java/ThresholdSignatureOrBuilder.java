// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BasicTypes.proto

package com.hederahashgraph.api.proto.java;

public interface ThresholdSignatureOrBuilder extends
    // @@protoc_insertion_point(interface_extends:proto.ThresholdSignature)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * for an N-of-M threshold key, this is a list of M signatures, at least N of which must be non-null
   * </pre>
   *
   * <code>.proto.SignatureList sigs = 2;</code>
   */
  boolean hasSigs();
  /**
   * <pre>
   * for an N-of-M threshold key, this is a list of M signatures, at least N of which must be non-null
   * </pre>
   *
   * <code>.proto.SignatureList sigs = 2;</code>
   */
  com.hederahashgraph.api.proto.java.SignatureList getSigs();
  /**
   * <pre>
   * for an N-of-M threshold key, this is a list of M signatures, at least N of which must be non-null
   * </pre>
   *
   * <code>.proto.SignatureList sigs = 2;</code>
   */
  com.hederahashgraph.api.proto.java.SignatureListOrBuilder getSigsOrBuilder();
}