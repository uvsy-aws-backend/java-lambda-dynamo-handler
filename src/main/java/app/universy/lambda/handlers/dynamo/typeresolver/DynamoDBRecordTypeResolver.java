package app.universy.lambda.handlers.dynamo.typeresolver;

import com.amazonaws.services.dynamodbv2.model.Record;

public class DynamoDBRecordTypeResolver {

    private static final String INSERT = "INSERT";
    private static final String MODIFY = "MODIFY";
    private static final String REMOVE = "REMOVE";

    private final Record record;

    public DynamoDBRecordTypeResolver(Record record) {
        this.record = record;
    }

    public boolean isModify() {
        return MODIFY.equals(record.getEventName());
    }

    public boolean isInsert() {
        return INSERT.equals(record.getEventName());
    }

    public boolean isRemove() {
        return REMOVE.equals(record.getEventName());
    }
}
