package com.universy.lambda.handlers.dynamo.consumers;

import java.util.function.Consumer;

public class DiscardConsumer implements Consumer<Object> {
    @Override
    public void accept(Object o) {
        // DO NOTHING. JUST DISCARD
    }
}
