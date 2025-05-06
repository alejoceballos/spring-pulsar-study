package momo2x.study.pulsar.service04;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;

import static java.lang.Runtime.getRuntime;
import static momo2x.study.pulsar.service04.Utils.SERVICE_URL;
import static org.slf4j.LoggerFactory.getLogger;

public class Main {

    private static final Logger log = getLogger(Main.class);

    public static void main(String[] args) {
        log.info("-> [Env] JAVA_TOOL_OPTIONS: \"{}\"", System.getenv("JAVA_TOOL_OPTIONS"));
        log.info("-> [Env] OTEL_SERVICE_NAME: \"{}\"", System.getenv("OTEL_SERVICE_NAME"));

        log.info(" --> Configuring OpenTelemetry SDK");
        final var openTelemetry = Config.getOpenTelemetrySdk();
        log.info(" --> OpenTelemetry SDK configured");

        try (final var pulsarClient =
                     PulsarClient
                             .builder()
                             .serviceUrl(SERVICE_URL)
                             .build()) {

            log.info(" --> PulsarClient \"{}\" successfully started", pulsarClient);

            new Consumer(pulsarClient).startListening();

        } catch (final PulsarClientException e) {
            log.info(" --> Failed to start PulsarClient");
            throw new RuntimeException(e);

        } finally {
            log.info(" --> Finishing the service execution");
        }

        getRuntime().addShutdownHook(new Thread(openTelemetry::close));
    }

}
