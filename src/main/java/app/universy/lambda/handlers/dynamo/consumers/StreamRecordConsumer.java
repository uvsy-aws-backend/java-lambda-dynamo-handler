package app.universy.lambda.handlers.dynamo.consumers;

import com.amazonaws.services.dynamodbv2.model.StreamRecord;

import java.util.function.Consumer;

public abstract class StreamRecordConsumer<T> implements Consumer<StreamRecord> {
    protected abstract Class<T> itemClass();
}
