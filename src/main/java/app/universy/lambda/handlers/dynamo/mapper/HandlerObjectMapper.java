package app.universy.lambda.handlers.dynamo.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerObjectMapper {

    private static ObjectMapper mapper;

    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}
