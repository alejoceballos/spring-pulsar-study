# Pulsar & OpenTelemetry

<!-- TOC -->
* [Pulsar & OpenTelemetry](#pulsar--opentelemetry)
  * [A Simple image of what I'm doing here](#a-simple-image-of-what-im-doing-here)
    * [What have I already achieved?](#what-have-i-already-achieved)
  * [OpenTelemetry references:](#opentelemetry-references)
    * [Check this out!](#check-this-out)
  * [Next Steps:](#next-steps)
<!-- TOC -->

## A Simple image of what I'm doing here

![Spring + Pulsar + OpenTelemetry](./README.files/Spring-Pulsar-OpenTelemetry.png)

### What have I already achieved?

1. Producing message to a pulsar topic
2. Consuming the message from the pulsar topic
3. Tracing using OTEL Java Agent and OTEL Collector
4. Logs and Tracing sent to Grafana through Tempo and Loki

## OpenTelemetry references:

- [Java Agent](https://opentelemetry.io/docs/zero-code/java/agent/) (Zero-code instrumentation)
  - [Annotations](https://opentelemetry.io/docs/zero-code/java/agent/annotations/) (@WithSpan, @SpanAttribute)
- [Spring Boot starter](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/)
  - [Getting started](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/getting-started/) (Maven Dependencies)
  - [Annotations](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/annotations/) (@WithSpan)
- [Collector](https://opentelemetry.io/docs/collector/)
  - [Install the Collector](https://opentelemetry.io/docs/collector/installation/) (Docker Compose)
  - [Collector Configuration](https://opentelemetry.io/docs/collector/configuration/) (not needed, use default)

### Check this out!

It seems to be a complete guide to put the entire ecosystem working!

- [A practical guide to implement OpenTelemetry in Spring Boot](https://vorozco.com/blog/2024/2024-11-18-A-practical-guide-spring-boot-open-telemetry.html)

## Next Steps:

- Tracing with Non-Spring Boot applications