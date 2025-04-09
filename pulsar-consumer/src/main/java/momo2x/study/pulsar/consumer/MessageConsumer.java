package momo2x.study.pulsar.consumer;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Message;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Component;

import static io.opentelemetry.api.trace.SpanKind.CONSUMER;
import static momo2x.study.pulsar.consumer.ConsumerTopicUtils.SPAN_NAME;
import static momo2x.study.pulsar.consumer.ConsumerTopicUtils.SUBSCRIPTION_NAME;
import static momo2x.study.pulsar.consumer.ConsumerTopicUtils.TOPIC_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    @PulsarListener(subscriptionName = SUBSCRIPTION_NAME, topics = TOPIC_NAME)
    @WithSpan(value = SPAN_NAME, kind = CONSUMER)
    void onTopicOneMessage(final Message<String> message) {
        final var span = Span.current();

        log.info(" --> MessageConsumer.onTopicOneMessage(...) - Trace ID: {}, Span ID: {}, Message: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message.getValue());
    }

}
