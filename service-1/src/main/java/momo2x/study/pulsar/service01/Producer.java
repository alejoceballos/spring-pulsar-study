package momo2x.study.pulsar.service01;

import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;

import static momo2x.study.pulsar.service01.Utils.TOPIC_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final PulsarTemplate<String> pulsarTemplate;

    public MessageId produce(final String message) {
        final var span = Span.current();

        final var messageId = pulsarTemplate
                .newMessage(message)
                .withTopic(TOPIC_NAME)
                .send();

        log.info(" --> Producer.produce(...) - Trace ID: {}, Span ID: {}, Message value: {}, Message ID: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message,
                messageId);

        return messageId;
    }

}
