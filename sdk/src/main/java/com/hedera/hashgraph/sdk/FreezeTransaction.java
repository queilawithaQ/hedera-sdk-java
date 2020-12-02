package com.hedera.hashgraph.sdk;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hedera.hashgraph.sdk.proto.FreezeServiceGrpc;
import com.hedera.hashgraph.sdk.proto.FreezeTransactionBody;
import com.hedera.hashgraph.sdk.proto.TransactionBody;
import com.hedera.hashgraph.sdk.proto.TransactionResponse;
import io.grpc.MethodDescriptor;

import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Set the freezing period in which the platform will stop creating events and accepting transactions.
 * This is used before safely shut down the platform for maintenance.
 */
public final class FreezeTransaction extends Transaction<FreezeTransaction> {
    private final FreezeTransactionBody.Builder builder;

    public FreezeTransaction() {
        builder = FreezeTransactionBody.newBuilder();
    }

    FreezeTransaction(LinkedHashMap<TransactionId, LinkedHashMap<AccountId, com.hedera.hashgraph.sdk.proto.Transaction>> txs) throws InvalidProtocolBufferException {
        super(txs);

        builder = bodyBuilder.getFreeze().toBuilder();
    }

    @SuppressWarnings("FromTemporalAccessor")
    public Instant getStartTime() {
        return Instant.from(OffsetTime.of(builder.getStartHour(), builder.getStartMin(), 0, 0, ZoneOffset.UTC));
    }

    /**
     * Sets the start time (in UTC).
     *
     * @param hour   The hour to be set
     * @param minute The minute to be set
     * @return {@code this}
     */
    public FreezeTransaction setStartTime(int hour, int minute) {
        requireNotFrozen();

        builder.setStartHour(hour);
        builder.setStartMin(minute);

        return this;
    }

    @SuppressWarnings("FromTemporalAccessor")
    public Instant getEndTime() {
        return Instant.from(OffsetTime.of(builder.getEndHour(), builder.getEndMin(), 0, 0, ZoneOffset.UTC));
    }

    /**
     * Sets the end time (in UTC).
     *
     * @param hour   The hour to be set
     * @param minute The minute to be set
     * @return {@code this}
     */
    public FreezeTransaction setEndTime(int hour, int minute) {
        requireNotFrozen();

        builder.setEndHour(hour);
        builder.setEndMin(minute);

        return this;
    }

    @Override
    MethodDescriptor<com.hedera.hashgraph.sdk.proto.Transaction, TransactionResponse> getMethodDescriptor() {
        return FreezeServiceGrpc.getFreezeMethod();
    }

    @Override
    boolean onFreeze(TransactionBody.Builder bodyBuilder) {
        bodyBuilder.setFreeze(builder);
        return true;
    }
}
