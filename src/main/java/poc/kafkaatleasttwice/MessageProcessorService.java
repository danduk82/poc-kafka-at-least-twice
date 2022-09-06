package poc.kafkaatleasttwice;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    @Async
    public void even(JsonNode key, JsonNode value){
        logger.info("even: " + value.get("payload").get("after").get("id").asText());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("finished even\n");
    }
    @Async
    public void odd(JsonNode key, JsonNode value){
        //logger.info("odd key: " + key.toPrettyString());
        logger.info("odd id: " + value.get("payload").get("after").get("id").asText());
        logger.info("finished odd\n");

    }

}
