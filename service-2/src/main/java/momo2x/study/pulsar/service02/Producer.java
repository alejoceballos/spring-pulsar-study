package momo2x.study.pulsar.service02;

import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;

import static momo2x.study.pulsar.service02.Utils.TOPIC_TWO_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final PulsarTemplate<String> pulsarTemplate;

    public void produce(final String message) {
        final var span = Span.current();

        final var messageId = pulsarTemplate
                .newMessage(message)
                .withTopic(TOPIC_TWO_NAME)
                .send();

        log.info(" --> Producer.produce(...) - Trace ID: {}, Span ID: {}, Message value: {}, Message ID: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message,
                messageId);
    }

}
