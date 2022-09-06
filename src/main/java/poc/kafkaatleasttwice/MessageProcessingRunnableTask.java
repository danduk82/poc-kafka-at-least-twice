package poc.kafkaatleasttwice;


import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageProcessingRunnableTask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    private JsonNode value;

    public MessageProcessingRunnableTask(JsonNode value){
        this.value = value;
    }

    @Override
    public void run() {
        logger.info("id: " + value.get("payload").get("after").get("id").asText() + " on thread "+Thread.currentThread().getName());
    }
}