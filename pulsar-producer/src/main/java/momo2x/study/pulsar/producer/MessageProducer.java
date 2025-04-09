package momo2x.study.pulsar.producer;

import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.MessageId;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;

import static momo2x.study.pulsar.producer.ProducerTopicUtils.TOPIC_NAME;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final PulsarTemplate<String> pulsarTemplate;

    public MessageId produceToTopicOne(final String message) {
        return pulsarTemplate
                .newMessage(message)
                .withTopic(TOPIC_NAME)
                .send();
    }

}
