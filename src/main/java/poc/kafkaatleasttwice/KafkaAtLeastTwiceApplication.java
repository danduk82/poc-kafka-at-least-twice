package poc.kafkaatleasttwice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Consumer;

@SpringBootApplication
public class KafkaAtLeastTwiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAtLeastTwiceApplication.class, args);
    }
}
