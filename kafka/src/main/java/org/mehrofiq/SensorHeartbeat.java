/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.mehrofiq;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class SensorHeartbeat extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2532032626993004569L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SensorHeartbeat\",\"namespace\":\"org.mehrofiq\",\"fields\":[{\"name\":\"SensorId\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SensorHeartbeat> ENCODER =
      new BinaryMessageEncoder<SensorHeartbeat>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SensorHeartbeat> DECODER =
      new BinaryMessageDecoder<SensorHeartbeat>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<SensorHeartbeat> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<SensorHeartbeat> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<SensorHeartbeat>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this SensorHeartbeat to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a SensorHeartbeat from a ByteBuffer. */
  public static SensorHeartbeat fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence SensorId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SensorHeartbeat() {}

  /**
   * All-args constructor.
   * @param SensorId The new value for SensorId
   */
  public SensorHeartbeat(java.lang.CharSequence SensorId) {
    this.SensorId = SensorId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return SensorId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: SensorId = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'SensorId' field.
   * @return The value of the 'SensorId' field.
   */
  public java.lang.CharSequence getSensorId() {
    return SensorId;
  }

  /**
   * Sets the value of the 'SensorId' field.
   * @param value the value to set.
   */
  public void setSensorId(java.lang.CharSequence value) {
    this.SensorId = value;
  }

  /**
   * Creates a new SensorHeartbeat RecordBuilder.
   * @return A new SensorHeartbeat RecordBuilder
   */
  public static org.mehrofiq.SensorHeartbeat.Builder newBuilder() {
    return new org.mehrofiq.SensorHeartbeat.Builder();
  }

  /**
   * Creates a new SensorHeartbeat RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SensorHeartbeat RecordBuilder
   */
  public static org.mehrofiq.SensorHeartbeat.Builder newBuilder(org.mehrofiq.SensorHeartbeat.Builder other) {
    return new org.mehrofiq.SensorHeartbeat.Builder(other);
  }

  /**
   * Creates a new SensorHeartbeat RecordBuilder by copying an existing SensorHeartbeat instance.
   * @param other The existing instance to copy.
   * @return A new SensorHeartbeat RecordBuilder
   */
  public static org.mehrofiq.SensorHeartbeat.Builder newBuilder(org.mehrofiq.SensorHeartbeat other) {
    return new org.mehrofiq.SensorHeartbeat.Builder(other);
  }

  /**
   * RecordBuilder for SensorHeartbeat instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SensorHeartbeat>
    implements org.apache.avro.data.RecordBuilder<SensorHeartbeat> {

    private java.lang.CharSequence SensorId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.mehrofiq.SensorHeartbeat.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.SensorId)) {
        this.SensorId = data().deepCopy(fields()[0].schema(), other.SensorId);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing SensorHeartbeat instance
     * @param other The existing instance to copy.
     */
    private Builder(org.mehrofiq.SensorHeartbeat other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.SensorId)) {
        this.SensorId = data().deepCopy(fields()[0].schema(), other.SensorId);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'SensorId' field.
      * @return The value.
      */
    public java.lang.CharSequence getSensorId() {
      return SensorId;
    }

    /**
      * Sets the value of the 'SensorId' field.
      * @param value The value of 'SensorId'.
      * @return This builder.
      */
    public org.mehrofiq.SensorHeartbeat.Builder setSensorId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.SensorId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'SensorId' field has been set.
      * @return True if the 'SensorId' field has been set, false otherwise.
      */
    public boolean hasSensorId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'SensorId' field.
      * @return This builder.
      */
    public org.mehrofiq.SensorHeartbeat.Builder clearSensorId() {
      SensorId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SensorHeartbeat build() {
      try {
        SensorHeartbeat record = new SensorHeartbeat();
        record.SensorId = fieldSetFlags()[0] ? this.SensorId : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SensorHeartbeat>
    WRITER$ = (org.apache.avro.io.DatumWriter<SensorHeartbeat>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SensorHeartbeat>
    READER$ = (org.apache.avro.io.DatumReader<SensorHeartbeat>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
