package com.universy.lambda.handlers.dynamo.mapper;

import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.universy.lambda.handlers.dynamo.exceptions.DynamoStreamConsumerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class DynamoItemMapper {

    private static final Logger LOGGER = LogManager.getLogger(DynamoItemMapper.class);

    public <T> T load(Map<String, AttributeValue> itemValues, Class<T> clazz) {
        try {
            String itemJson = ItemUtils.toItem(itemValues).toJSON();
            return HandlerObjectMapper.getMapper().readValue(itemJson, clazz);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new DynamoStreamConsumerException();
        }
    }
}
