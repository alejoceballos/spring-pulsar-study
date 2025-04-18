package momo2x.study.pulsar.service01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        log.info("-> [Env] JAVA_TOOL_OPTIONS: \"{}\"", System.getenv("JAVA_TOOL_OPTIONS"));
        log.info("-> [Env] OTEL_SERVICE_NAME: \"{}\"", System.getenv("OTEL_SERVICE_NAME"));
        run(Main.class, args);
    }

}
