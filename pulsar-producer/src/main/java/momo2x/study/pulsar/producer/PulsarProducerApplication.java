package momo2x.study.pulsar.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
public class PulsarProducerApplication {

    public static void main(String[] args) {
        log.info("-> [Env] JAVA_TOOL_OPTIONS: \"{}\"", System.getenv("JAVA_TOOL_OPTIONS"));
        log.info("-> [Env] OTEL_SERVICE_NAME: \"{}\"", System.getenv("OTEL_SERVICE_NAME"));
        run(PulsarProducerApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(MessageProducer messageProducer) {
        return args -> messageProducer.produceToTopicOne();
    }

}
