package poc.kafkaatleasttwice;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class MessageProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    @Value("${poc.even.time-delta}")
    Long evenTimeDelta;

    @Value("${poc.odd.time-delta}")
    Long oddTimeDelta;

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public MessageProcessorService(
            ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public void even(JsonNode key, JsonNode value){
        threadPoolTaskScheduler.schedule(
                new MessageProcessingRunnableTask(value),
                new Date(value.get("payload").get("source").get("ts_ms").asLong() + evenTimeDelta)
        );
    }

    public void odd(JsonNode key, JsonNode value){
        threadPoolTaskScheduler.schedule(
                new MessageProcessingRunnableTask(value),
                new Date(value.get("payload").get("source").get("ts_ms").asLong() + oddTimeDelta)
        );
    }

}
