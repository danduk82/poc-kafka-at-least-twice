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
    @Bean
    public Consumer<Integer> even() {
        return value -> System.out.println("EVEN: " + value);
    }

    @Bean
    public Consumer<Integer> odd() {
        return value -> System.out.println("ODD: " + value);
    }
}
