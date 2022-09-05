package poc.kafkaatleasttwice;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("process")
public class Process implements java.util.function.Consumer<KStream<JsonNode, JsonNode>> {

    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    @Override
    public void accept(KStream<JsonNode, JsonNode> input) {
        input.foreach((key, value) -> routing(key, value));

    }

    public static void routing(JsonNode key, JsonNode value){
        if (key.get("payload").get("id").asInt() % 2 == 0){
            even(key, value);
        } else {
            odd(key, value);
        }

    }
    public static void even(JsonNode key, JsonNode value){
        logger.info("even: " + value.get("payload").get("after").get("id").asText());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        logger.info("finished waiting\n");
    }
    public static void odd(JsonNode key, JsonNode value){
        logger.info("odd key: " + key.toPrettyString());
        logger.info("odd id: " + value.get("payload").get("after").get("id").asText());
    }

}
