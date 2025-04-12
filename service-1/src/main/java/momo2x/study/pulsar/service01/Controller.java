package momo2x.study.pulsar.service01;

import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/producer", produces = {APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class Controller {

    private final Producer producer;

    @PostMapping
    public ResponseEntity<MessageId> sendToTopicOne(@RequestBody final String message) {
        final var span = Span.current();

        log.info(" --> Controller.sendToTopicOne(...) - Trace ID: {}, Span ID: {}, Message: {}",
                span.getSpanContext().getTraceId(),
                span.getSpanContext().getSpanId(),
                message);

        final var messageId = producer.produce(message);

        return ResponseEntity
                .status(CREATED)
                .body(messageId);
    }

}
