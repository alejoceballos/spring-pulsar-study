package momo2x.study.pulsar.service03;

import io.opentelemetry.api.trace.Span;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.StringSchema;
import org.slf4j.Logger;

import static java.lang.Thread.sleep;
import static momo2x.study.pulsar.service03.Utils.SUBSCRIPTION_NAME;
import static momo2x.study.pulsar.service03.Utils.TOPIC_TWO_NAME;
import static org.slf4j.LoggerFactory.getLogger;

public class Consumer {

    private static final Logger log = getLogger(Consumer.class);

    private final PulsarClient pulsarClient;
    private final Producer producer;

    public Consumer(final PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        this.producer = new Producer(pulsarClient);
    }

    public void startListening() {
        try (final var consumer =
                     pulsarClient
                             .newConsumer(new StringSchema())
                             .topic(TOPIC_TWO_NAME)
                             .subscriptionName(SUBSCRIPTION_NAME)
                             .messageListener(this::processMessage)
                             .subscribe()) {

            log.info(" --> Consumer \"{}\" successfully started", consumer.getConsumerName());

            //noinspection SingleStatementInBlock,InfiniteLoopStatement
            while (true) {
                //noinspection BusyWait
                sleep(1000);
            }

        } catch (final PulsarClientException e) {
            log.info(" --> Failed to start Consumer");
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        } finally {
            log.info(" --> Finishing the consumer execution");
        }
    }

    private void processMessage(
            final org.apache.pulsar.client.api.Consumer<String> consumer,
            final Message<String> message) {
        final var span = Span.current();

        try {
            log.info(" --> Consumer.processMessage(...) - Trace ID: {}, Span ID: {}, Message: {}",
                    span.getSpanContext().getTraceId(),
                    span.getSpanContext().getSpanId(),
                    message.getValue());

            producer.produce(message.getValue());

            consumer.acknowledge(message);

        } catch (Exception e) {
            consumer.negativeAcknowledge(message);
        }
    }
}
