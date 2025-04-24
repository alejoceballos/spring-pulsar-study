package momo2x.study.pulsar.service04;

import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

public class Config {

    private static OpenTelemetrySdk openTelemetrySdk;

    public static void autoconfiguredSdk() {
        if (openTelemetrySdk == null) {
            openTelemetrySdk = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
        }
    }

    public static OpenTelemetrySdk getOpenTelemetrySdk() {
        autoconfiguredSdk();
        return openTelemetrySdk;
    }

    public static Tracer getTracer() {
        return getOpenTelemetrySdk().getTracer("test");
    }

}
