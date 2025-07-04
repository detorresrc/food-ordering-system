/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.detorresrc.foodorderingsystem.kafka.order.avro.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class RestaurantApprovalRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = -352220115901672086L;


    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RestaurantApprovalRequestAvroModel\",\"namespace\":\"com.detorresrc.foodorderingsystem.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"restaurantId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"restaurantOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"RestaurantOrderStatus\",\"symbols\":[\"PAID\"]}},{\"name\":\"products\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Product\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    private static final SpecificData MODEL$ = new SpecificData();

    static {
        MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
        MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
        MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
    }

    private static final BinaryMessageEncoder<RestaurantApprovalRequestAvroModel> ENCODER =
        new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> DECODER =
        new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<RestaurantApprovalRequestAvroModel> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this RestaurantApprovalRequestAvroModel to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a RestaurantApprovalRequestAvroModel from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a RestaurantApprovalRequestAvroModel instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static RestaurantApprovalRequestAvroModel fromByteBuffer(
        java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private java.util.UUID id;
    private java.util.UUID sagaId;
    private java.util.UUID restaurantId;
    private java.util.UUID orderId;
    private com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus;
    private java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public RestaurantApprovalRequestAvroModel() {
    }

    /**
     * All-args constructor.
     * @param id The new value for id
     * @param sagaId The new value for sagaId
     * @param restaurantId The new value for restaurantId
     * @param orderId The new value for orderId
     * @param restaurantOrderStatus The new value for restaurantOrderStatus
     * @param products The new value for products
     * @param price The new value for price
     * @param createdAt The new value for createdAt
     */
    public RestaurantApprovalRequestAvroModel(java.util.UUID id, java.util.UUID sagaId, java.util.UUID restaurantId, java.util.UUID orderId, com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus, java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> products, java.math.BigDecimal price, java.time.Instant createdAt) {
        this.id = id;
        this.sagaId = sagaId;
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.restaurantOrderStatus = restaurantOrderStatus;
        this.products = products;
        this.price = price;
        this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    }

    @Override
    public org.apache.avro.specific.SpecificData getSpecificData() {
        return MODEL$;
    }

    @Override
    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    @Override
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return id;
            case 1:
                return sagaId;
            case 2:
                return restaurantId;
            case 3:
                return orderId;
            case 4:
                return restaurantOrderStatus;
            case 5:
                return products;
            case 6:
                return price;
            case 7:
                return createdAt;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    private static final org.apache.avro.Conversion<?>[] conversions =
        new org.apache.avro.Conversion<?>[]{
            new org.apache.avro.Conversions.UUIDConversion(),
            new org.apache.avro.Conversions.UUIDConversion(),
            new org.apache.avro.Conversions.UUIDConversion(),
            new org.apache.avro.Conversions.UUIDConversion(),
            null,
            null,
            new org.apache.avro.Conversions.DecimalConversion(),
            new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
            null
        };

    @Override
    public org.apache.avro.Conversion<?> getConversion(int field) {
        return conversions[field];
    }

    // Used by DatumReader.  Applications should not call.
    @Override
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                id = (java.util.UUID) value$;
                break;
            case 1:
                sagaId = (java.util.UUID) value$;
                break;
            case 2:
                restaurantId = (java.util.UUID) value$;
                break;
            case 3:
                orderId = (java.util.UUID) value$;
                break;
            case 4:
                restaurantOrderStatus = (com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus) value$;
                break;
            case 5:
                products = (java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product>) value$;
                break;
            case 6:
                price = (java.math.BigDecimal) value$;
                break;
            case 7:
                createdAt = (java.time.Instant) value$;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'id' field.
     * @return The value of the 'id' field.
     */
    public java.util.UUID getId() {
        return id;
    }


    /**
     * Sets the value of the 'id' field.
     * @param value the value to set.
     */
    public void setId(java.util.UUID value) {
        this.id = value;
    }

    /**
     * Gets the value of the 'sagaId' field.
     * @return The value of the 'sagaId' field.
     */
    public java.util.UUID getSagaId() {
        return sagaId;
    }


    /**
     * Sets the value of the 'sagaId' field.
     * @param value the value to set.
     */
    public void setSagaId(java.util.UUID value) {
        this.sagaId = value;
    }

    /**
     * Gets the value of the 'restaurantId' field.
     * @return The value of the 'restaurantId' field.
     */
    public java.util.UUID getRestaurantId() {
        return restaurantId;
    }


    /**
     * Sets the value of the 'restaurantId' field.
     * @param value the value to set.
     */
    public void setRestaurantId(java.util.UUID value) {
        this.restaurantId = value;
    }

    /**
     * Gets the value of the 'orderId' field.
     * @return The value of the 'orderId' field.
     */
    public java.util.UUID getOrderId() {
        return orderId;
    }


    /**
     * Sets the value of the 'orderId' field.
     * @param value the value to set.
     */
    public void setOrderId(java.util.UUID value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the 'restaurantOrderStatus' field.
     * @return The value of the 'restaurantOrderStatus' field.
     */
    public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus getRestaurantOrderStatus() {
        return restaurantOrderStatus;
    }


    /**
     * Sets the value of the 'restaurantOrderStatus' field.
     * @param value the value to set.
     */
    public void setRestaurantOrderStatus(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus value) {
        this.restaurantOrderStatus = value;
    }

    /**
     * Gets the value of the 'products' field.
     * @return The value of the 'products' field.
     */
    public java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> getProducts() {
        return products;
    }


    /**
     * Sets the value of the 'products' field.
     * @param value the value to set.
     */
    public void setProducts(java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> value) {
        this.products = value;
    }

    /**
     * Gets the value of the 'price' field.
     * @return The value of the 'price' field.
     */
    public java.math.BigDecimal getPrice() {
        return price;
    }


    /**
     * Sets the value of the 'price' field.
     * @param value the value to set.
     */
    public void setPrice(java.math.BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the 'createdAt' field.
     * @return The value of the 'createdAt' field.
     */
    public java.time.Instant getCreatedAt() {
        return createdAt;
    }


    /**
     * Sets the value of the 'createdAt' field.
     * @param value the value to set.
     */
    public void setCreatedAt(java.time.Instant value) {
        this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    }

    /**
     * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder.
     * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
     */
    public static com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder() {
        return new com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
    }

    /**
     * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
     */
    public static com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder other) {
        if (other == null) {
            return new com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
        } else {
            return new com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder(other);
        }
    }

    /**
     * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder by copying an existing RestaurantApprovalRequestAvroModel instance.
     * @param other The existing instance to copy.
     * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
     */
    public static com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel other) {
        if (other == null) {
            return new com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
        } else {
            return new com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder(other);
        }
    }

    /**
     * RecordBuilder for RestaurantApprovalRequestAvroModel instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RestaurantApprovalRequestAvroModel>
        implements org.apache.avro.data.RecordBuilder<RestaurantApprovalRequestAvroModel> {

        private java.util.UUID id;
        private java.util.UUID sagaId;
        private java.util.UUID restaurantId;
        private java.util.UUID orderId;
        private com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus;
        private java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> products;
        private java.math.BigDecimal price;
        private java.time.Instant createdAt;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$, MODEL$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.sagaId)) {
                this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.restaurantId)) {
                this.restaurantId = data().deepCopy(fields()[2].schema(), other.restaurantId);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
            if (isValidValue(fields()[3], other.orderId)) {
                this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
                fieldSetFlags()[3] = other.fieldSetFlags()[3];
            }
            if (isValidValue(fields()[4], other.restaurantOrderStatus)) {
                this.restaurantOrderStatus = data().deepCopy(fields()[4].schema(), other.restaurantOrderStatus);
                fieldSetFlags()[4] = other.fieldSetFlags()[4];
            }
            if (isValidValue(fields()[5], other.products)) {
                this.products = data().deepCopy(fields()[5].schema(), other.products);
                fieldSetFlags()[5] = other.fieldSetFlags()[5];
            }
            if (isValidValue(fields()[6], other.price)) {
                this.price = data().deepCopy(fields()[6].schema(), other.price);
                fieldSetFlags()[6] = other.fieldSetFlags()[6];
            }
            if (isValidValue(fields()[7], other.createdAt)) {
                this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
                fieldSetFlags()[7] = other.fieldSetFlags()[7];
            }
        }

        /**
         * Creates a Builder by copying an existing RestaurantApprovalRequestAvroModel instance
         * @param other The existing instance to copy.
         */
        private Builder(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel other) {
            super(SCHEMA$, MODEL$);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.sagaId)) {
                this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.restaurantId)) {
                this.restaurantId = data().deepCopy(fields()[2].schema(), other.restaurantId);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.orderId)) {
                this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.restaurantOrderStatus)) {
                this.restaurantOrderStatus = data().deepCopy(fields()[4].schema(), other.restaurantOrderStatus);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.products)) {
                this.products = data().deepCopy(fields()[5].schema(), other.products);
                fieldSetFlags()[5] = true;
            }
            if (isValidValue(fields()[6], other.price)) {
                this.price = data().deepCopy(fields()[6].schema(), other.price);
                fieldSetFlags()[6] = true;
            }
            if (isValidValue(fields()[7], other.createdAt)) {
                this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
                fieldSetFlags()[7] = true;
            }
        }

        /**
         * Gets the value of the 'id' field.
         * @return The value.
         */
        public java.util.UUID getId() {
            return id;
        }


        /**
         * Sets the value of the 'id' field.
         * @param value The value of 'id'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setId(java.util.UUID value) {
            validate(fields()[0], value);
            this.id = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'id' field has been set.
         * @return True if the 'id' field has been set, false otherwise.
         */
        public boolean hasId() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'id' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearId() {
            id = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'sagaId' field.
         * @return The value.
         */
        public java.util.UUID getSagaId() {
            return sagaId;
        }


        /**
         * Sets the value of the 'sagaId' field.
         * @param value The value of 'sagaId'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setSagaId(java.util.UUID value) {
            validate(fields()[1], value);
            this.sagaId = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'sagaId' field has been set.
         * @return True if the 'sagaId' field has been set, false otherwise.
         */
        public boolean hasSagaId() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'sagaId' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearSagaId() {
            sagaId = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'restaurantId' field.
         * @return The value.
         */
        public java.util.UUID getRestaurantId() {
            return restaurantId;
        }


        /**
         * Sets the value of the 'restaurantId' field.
         * @param value The value of 'restaurantId'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setRestaurantId(java.util.UUID value) {
            validate(fields()[2], value);
            this.restaurantId = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'restaurantId' field has been set.
         * @return True if the 'restaurantId' field has been set, false otherwise.
         */
        public boolean hasRestaurantId() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'restaurantId' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearRestaurantId() {
            restaurantId = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'orderId' field.
         * @return The value.
         */
        public java.util.UUID getOrderId() {
            return orderId;
        }


        /**
         * Sets the value of the 'orderId' field.
         * @param value The value of 'orderId'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setOrderId(java.util.UUID value) {
            validate(fields()[3], value);
            this.orderId = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'orderId' field has been set.
         * @return True if the 'orderId' field has been set, false otherwise.
         */
        public boolean hasOrderId() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'orderId' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearOrderId() {
            orderId = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'restaurantOrderStatus' field.
         * @return The value.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus getRestaurantOrderStatus() {
            return restaurantOrderStatus;
        }


        /**
         * Sets the value of the 'restaurantOrderStatus' field.
         * @param value The value of 'restaurantOrderStatus'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setRestaurantOrderStatus(com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus value) {
            validate(fields()[4], value);
            this.restaurantOrderStatus = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'restaurantOrderStatus' field has been set.
         * @return True if the 'restaurantOrderStatus' field has been set, false otherwise.
         */
        public boolean hasRestaurantOrderStatus() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'restaurantOrderStatus' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearRestaurantOrderStatus() {
            restaurantOrderStatus = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        /**
         * Gets the value of the 'products' field.
         * @return The value.
         */
        public java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> getProducts() {
            return products;
        }


        /**
         * Sets the value of the 'products' field.
         * @param value The value of 'products'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setProducts(java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product> value) {
            validate(fields()[5], value);
            this.products = value;
            fieldSetFlags()[5] = true;
            return this;
        }

        /**
         * Checks whether the 'products' field has been set.
         * @return True if the 'products' field has been set, false otherwise.
         */
        public boolean hasProducts() {
            return fieldSetFlags()[5];
        }


        /**
         * Clears the value of the 'products' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearProducts() {
            products = null;
            fieldSetFlags()[5] = false;
            return this;
        }

        /**
         * Gets the value of the 'price' field.
         * @return The value.
         */
        public java.math.BigDecimal getPrice() {
            return price;
        }


        /**
         * Sets the value of the 'price' field.
         * @param value The value of 'price'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setPrice(java.math.BigDecimal value) {
            validate(fields()[6], value);
            this.price = value;
            fieldSetFlags()[6] = true;
            return this;
        }

        /**
         * Checks whether the 'price' field has been set.
         * @return True if the 'price' field has been set, false otherwise.
         */
        public boolean hasPrice() {
            return fieldSetFlags()[6];
        }


        /**
         * Clears the value of the 'price' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearPrice() {
            price = null;
            fieldSetFlags()[6] = false;
            return this;
        }

        /**
         * Gets the value of the 'createdAt' field.
         * @return The value.
         */
        public java.time.Instant getCreatedAt() {
            return createdAt;
        }


        /**
         * Sets the value of the 'createdAt' field.
         * @param value The value of 'createdAt'.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setCreatedAt(java.time.Instant value) {
            validate(fields()[7], value);
            this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
            fieldSetFlags()[7] = true;
            return this;
        }

        /**
         * Checks whether the 'createdAt' field has been set.
         * @return True if the 'createdAt' field has been set, false otherwise.
         */
        public boolean hasCreatedAt() {
            return fieldSetFlags()[7];
        }


        /**
         * Clears the value of the 'createdAt' field.
         * @return This builder.
         */
        public com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearCreatedAt() {
            fieldSetFlags()[7] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public RestaurantApprovalRequestAvroModel build() {
            try {
                RestaurantApprovalRequestAvroModel record = new RestaurantApprovalRequestAvroModel();
                record.id = fieldSetFlags()[0] ? this.id : (java.util.UUID) defaultValue(fields()[0]);
                record.sagaId = fieldSetFlags()[1] ? this.sagaId : (java.util.UUID) defaultValue(fields()[1]);
                record.restaurantId = fieldSetFlags()[2] ? this.restaurantId : (java.util.UUID) defaultValue(fields()[2]);
                record.orderId = fieldSetFlags()[3] ? this.orderId : (java.util.UUID) defaultValue(fields()[3]);
                record.restaurantOrderStatus = fieldSetFlags()[4] ? this.restaurantOrderStatus : (com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantOrderStatus) defaultValue(fields()[4]);
                record.products = fieldSetFlags()[5] ? this.products : (java.util.List<com.detorresrc.foodorderingsystem.kafka.order.avro.model.Product>) defaultValue(fields()[5]);
                record.price = fieldSetFlags()[6] ? this.price : (java.math.BigDecimal) defaultValue(fields()[6]);
                record.createdAt = fieldSetFlags()[7] ? this.createdAt : (java.time.Instant) defaultValue(fields()[7]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<RestaurantApprovalRequestAvroModel>
        WRITER$ = (org.apache.avro.io.DatumWriter<RestaurantApprovalRequestAvroModel>) MODEL$.createDatumWriter(SCHEMA$);

    @Override
    public void writeExternal(java.io.ObjectOutput out)
        throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<RestaurantApprovalRequestAvroModel>
        READER$ = (org.apache.avro.io.DatumReader<RestaurantApprovalRequestAvroModel>) MODEL$.createDatumReader(SCHEMA$);

    @Override
    public void readExternal(java.io.ObjectInput in)
        throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

}










