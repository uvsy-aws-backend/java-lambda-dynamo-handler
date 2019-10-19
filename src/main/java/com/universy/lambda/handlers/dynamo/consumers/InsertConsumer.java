package com.universy.lambda.handlers.dynamo.consumers;

import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.universy.lambda.handlers.dynamo.mapper.DynamoItemMapper;

public abstract class InsertConsumer<T> extends StreamRecordConsumer<T> {

    @Override
    public void accept(StreamRecord streamRecord) {

        DynamoItemMapper dynamoItemMapper = new DynamoItemMapper();

        T newItem = dynamoItemMapper.load(streamRecord.getNewImage(), itemClass());

        this.insert(newItem);
    }

    protected abstract void insert(T newItem);
}
