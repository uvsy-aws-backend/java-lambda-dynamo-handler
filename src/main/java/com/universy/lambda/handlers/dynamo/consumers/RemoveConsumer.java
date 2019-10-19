package com.universy.lambda.handlers.dynamo.consumers;

import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.universy.lambda.handlers.dynamo.mapper.DynamoItemMapper;

public abstract class RemoveConsumer<T> extends StreamRecordConsumer<T> {

    @Override
    public void accept(StreamRecord streamRecord) {

        DynamoItemMapper dynamoItemMapper = new DynamoItemMapper();

        T item = dynamoItemMapper.load(streamRecord.getOldImage(), itemClass());

        this.remove(item);
    }

    protected abstract void remove(T item);
}
