package momo2x.study.pulsar.producer;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.opentelemetry.api.trace.SpanKind.PRODUCER;
import static momo2x.study.pulsar.producer.ProducerTopicUtils.SPAN_NAME;
import static momo2x.study.pulsar.producer.ProducerTopicUtils.TOPIC_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final PulsarTemplate<String> pulsarTemplate;

    @WithSpan(value = SPAN_NAME, kind = PRODUCER)
    public void produceToTopicOne() {
        final var span = Span.current().addEvent("Span Event: " + TOPIC_NAME);
        final var message = UUID.randomUUID().toString();

        log.info("-> produceToTopicOne - Trace ID: {}, Message: {}",
                span.getSpanContext().getTraceId(),
                message);

        pulsarTemplate
                .newMessage(message)
                .withTopic(TOPIC_NAME)
                .send();
    }

}
