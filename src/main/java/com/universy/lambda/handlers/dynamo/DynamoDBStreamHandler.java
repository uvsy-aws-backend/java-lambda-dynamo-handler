package com.universy.lambda.handlers.dynamo;

import com.amazonaws.services.dynamodbv2.model.Record;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.universy.lambda.handlers.dynamo.consumers.DiscardConsumer;
import com.universy.lambda.handlers.dynamo.typeresolver.DynamoDBRecordTypeResolver;

import java.util.function.Consumer;

public abstract class DynamoDBStreamHandler<T> implements RequestHandler<DynamodbEvent, Void> {

    private final Consumer<Object> discardConsumer;

    public DynamoDBStreamHandler() {
        discardConsumer = new DiscardConsumer();
    }

    @Override
    public Void handleRequest(DynamodbEvent dynamodbEvent, Context context) {
        dynamodbEvent.getRecords().forEach(this::accept);
        return null;
    }

    private void accept(Record record) {
        try {
            StreamRecord streamRecord = record.getDynamodb();
            this.resolverConsumer(record).accept(streamRecord);
        } catch (Exception e) {
            discardConsumer.accept(record);
        }
    }

    private Consumer<? super StreamRecord> resolverConsumer(Record record) {
        DynamoDBRecordTypeResolver typeResolver = new DynamoDBRecordTypeResolver(record);
        if (typeResolver.isInsert()) {
            return getInsertConsumer();
        } else if (typeResolver.isModify()) {
            return getModifyConsumer();
        } else if (typeResolver.isRemove()) {
            return getRemoveConsumer();
        } else {
            return discardConsumer;
        }
    }

    protected Consumer<? super StreamRecord> getInsertConsumer() {
        return discardConsumer;
    }

    protected Consumer<? super StreamRecord> getModifyConsumer() {
        return discardConsumer;
    }

    protected Consumer<? super StreamRecord> getRemoveConsumer() {
        return discardConsumer;
    }
}
