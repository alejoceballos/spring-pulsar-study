package momo2x.study.pulsar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

/**
 * OpenTelemetry reference:
 * <p>
 *   - <a href="https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/">Spring Boot starter</a>
 *   - <a href="https://opentelemetry.io/docs/collector/installation/">Install the Collector</a> (Docker Compose)
 *   - <a href="https://opentelemetry.io/docs/collector/configuration/">Collector Configuration</a> (not needed, use default)
 * </p>
 */
@Slf4j
@SpringBootApplication
public class PulsarConsumerApplication {

    public static void main(String[] args) {
        log.info("-> [Env] JAVA_TOOL_OPTIONS: \"{}\"", System.getenv("JAVA_TOOL_OPTIONS"));
        log.info("-> [Env] OTEL_SERVICE_NAME: \"{}\"", System.getenv("OTEL_SERVICE_NAME"));
        run(PulsarConsumerApplication.class, args);
    }

}
