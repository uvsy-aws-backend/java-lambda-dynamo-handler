package com.universy.lambda.handlers.dynamo.consumers;

import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.universy.lambda.handlers.dynamo.mapper.DynamoItemMapper;

public abstract class ModifyConsumer<T> extends StreamRecordConsumer<T> {

    @Override
    public void accept(StreamRecord streamRecord) {

        DynamoItemMapper dynamoItemMapper = new DynamoItemMapper();

        T oldItem = dynamoItemMapper.load(streamRecord.getOldImage(), itemClass());
        T newItem = dynamoItemMapper.load(streamRecord.getNewImage(), itemClass());

        this.modify(oldItem, newItem);
    }

    protected abstract void modify(T oldItem, T newItem);
}
