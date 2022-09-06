package poc.kafkaatleasttwice;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("process")
//public class Process implements java.util.function.Consumer<Flux<KStream<JsonNode, JsonNode>>, Flux<KStream<JsonNode, JsonNode>>> {
public class Process implements java.util.function.Consumer<KStream<JsonNode, JsonNode>> {
    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    private MessageProcessorService messageProcessorService;

    public Process(MessageProcessorService messageProcessorService) {
        this.messageProcessorService = messageProcessorService;
    }

    @Override
    public void accept(KStream<JsonNode, JsonNode> input) {
        input.foreach((key, value) -> routing(key, value));

    }

    public void routing(JsonNode key, JsonNode value){
        if (key.get("payload").get("id").asInt() % 2 == 0){
            messageProcessorService.even(key, value);
        } else {
            messageProcessorService.odd(key, value);
        }

    }



}
