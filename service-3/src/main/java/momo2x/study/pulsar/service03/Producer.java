package momo2x.study.pulsar.service03;

import io.opentelemetry.api.trace.Span;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;

import static momo2x.study.pulsar.service03.Utils.PRODUCER_NAME;
import static momo2x.study.pulsar.service03.Utils.TOPIC_THREE_NAME;
import static org.apache.pulsar.client.api.Schema.STRING;
import static org.slf4j.LoggerFactory.getLogger;

public class Producer {

    private static final Logger log = getLogger(Producer.class);

    private final org.apache.pulsar.client.api.Producer<String> producer;

    public Producer(final PulsarClient pulsarClient) {
        try {
            this.producer = pulsarClient
                    .newProducer(STRING)
                    .producerName(PRODUCER_NAME)
                    .topic(TOPIC_THREE_NAME)
                    .create();

        } catch (final PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    public void produce(final String message) throws PulsarClientException {
        final var span = Span.current();

        final var messageId = producer
                .newMessage(STRING)
                .value(message)
                .send();

        log.info(" --> Producer.produce(...) - Trace ID: {}, Span ID: {}, Message value: {}, Message ID: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message,
                messageId);
    }

}
