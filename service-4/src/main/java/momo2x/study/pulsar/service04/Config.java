package momo2x.study.pulsar.service04;

import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.EnvironmentResourceProvider;
import io.opentelemetry.sdk.autoconfigure.spi.internal.DefaultConfigProperties;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

import java.util.Map;

public class Config {

    private static OpenTelemetrySdk openTelemetrySdk;

    public static void configuredSdk() {
        if (openTelemetrySdk == null) {
            final var resource =
                    new EnvironmentResourceProvider()
                            .createResource(
                                    DefaultConfigProperties.create(Map.of()));

            final var tracerProvider = SdkTracerProvider
                    .builder()
                    .setResource(resource)
                    .addSpanProcessor(SimpleSpanProcessor.create(OtlpGrpcSpanExporter.getDefault()))
                    .build();

            final var contextPropagators = ContextPropagators.create(
                    TextMapPropagator.composite(
                            W3CTraceContextPropagator.getInstance(),
                            W3CBaggagePropagator.getInstance()));

            openTelemetrySdk =
                    OpenTelemetrySdk.builder()
                            .setTracerProvider(tracerProvider)
                            .setPropagators(contextPropagators)
                            .build();
        }
    }

    public static OpenTelemetrySdk getOpenTelemetrySdk() {
        configuredSdk();
        return openTelemetrySdk;
    }

    public static Tracer getTracer() {
        return getOpenTelemetrySdk().getTracer("momo2x.study.pulsar");
    }

}
