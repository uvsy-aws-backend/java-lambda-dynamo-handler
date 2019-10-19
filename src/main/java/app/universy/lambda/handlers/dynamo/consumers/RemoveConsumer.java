package app.universy.lambda.handlers.dynamo.consumers;

import app.universy.lambda.handlers.dynamo.mapper.DynamoItemMapper;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;

public abstract class RemoveConsumer<T> extends StreamRecordConsumer<T> {

    @Override
    public void accept(StreamRecord streamRecord) {

        DynamoItemMapper dynamoItemMapper = new DynamoItemMapper();

        T item = dynamoItemMapper.load(streamRecord.getOldImage(), itemClass());

        this.remove(item);
    }

    protected abstract void remove(T item);
}
