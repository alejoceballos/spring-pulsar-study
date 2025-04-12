package momo2x.study.pulsar.service02;

import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Message;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Component;

import static momo2x.study.pulsar.service02.Utils.SUBSCRIPTION_NAME;
import static momo2x.study.pulsar.service02.Utils.TOPIC_ONE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private final Producer producer;

    @PulsarListener(subscriptionName = SUBSCRIPTION_NAME, topics = TOPIC_ONE_NAME)
    void onTopicOneMessage(final Message<String> message) {
        final var span = Span.current();

        log.info(" --> Consumer.onTopicOneMessage(...) - Trace ID: {}, Span ID: {}, Message: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message.getValue());

        producer.produce(message.getValue());
    }

}
