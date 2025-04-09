package momo2x.study.pulsar.producer;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.opentelemetry.api.trace.SpanKind.PRODUCER;
import static momo2x.study.pulsar.producer.ProducerTopicUtils.SPAN_NAME;
import static momo2x.study.pulsar.producer.ProducerTopicUtils.TOPIC_NAME;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/producer", produces = {APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ProducerController {

    private final MessageProducer messageProducer;

    @PostMapping
    @WithSpan(value = SPAN_NAME, kind = PRODUCER)
    public ResponseEntity<MessageId> sendToTopicOne(@RequestBody String message) {
        final var span = Span.current().addEvent("Span Event: " + TOPIC_NAME);

        log.info(" --> ProducerController.sendToTopicOne(...) - Trace ID: {}, Span ID: {}, Message: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message);

        final var messageId = messageProducer.produceToTopicOne(message);

        return ResponseEntity
                .status(CREATED)
                .body(messageId);
    }

}
