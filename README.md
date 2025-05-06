# Pulsar & OpenTelemetry

<!-- TOC -->
* [Pulsar & OpenTelemetry](#pulsar--opentelemetry)
  * [A Simple image of what I'm doing here](#a-simple-image-of-what-im-doing-here)
    * [Description](#description)
      * [Technologies](#technologies)
      * [Architecture Elements](#architecture-elements)
      * [Flow](#flow)
    * [What have I already achieved?](#what-have-i-already-achieved)
  * [Pulsar references:](#pulsar-references)
  * [OpenTelemetry references:](#opentelemetry-references)
  * [Logback references](#logback-references)
  * [Docker references](#docker-references)
  * [Next Steps:](#next-steps)
    * [Yet to achieve:](#yet-to-achieve)
    * [Use Grafana Alloy instead of OTEL Collector](#use-grafana-alloy-instead-of-otel-collector)
    * [Split a message into two or more. All of them must have the same trace ID](#split-a-message-into-two-or-more-all-of-them-must-have-the-same-trace-id)
    * [Keep applications alive](#keep-applications-alive)
<!-- TOC -->

## A Simple image of what I'm doing here

![Spring + Pulsar + OpenTelemetry](README.files/Spring-Pulsar-OpenTelemetry.png)

### Description

#### Technologies

| Icon                                                   | Name                                                                     | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|--------------------------------------------------------|--------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![Apache Pulsar](README.files/Logo-Apache-Pulsar.png)  | [Apache Pulsar](https://pulsar.apache.org/docs/4.0.x/concepts-overview/) | Distributed Messaging and Streaming tool. It is a multi-tenant, high-performance solution for server-to-server messaging. In the context of this study, is the responsible for making messages reach their destination, from service to service and it is containerized using [Docker](https://docs.docker.com/engine/) technology. For the [Java technology](https://www.java.com/en/), there is a Java client that allows creating Pulsar producers, consumers, and readers and perform administrative tasks, all thread-safe.               |
| ![Docker](README.files/Logo-Docker.png)                | [Docker](https://docs.docker.com/engine/)                                | Docker is a software platform that helps developers package their software into containers, which include everything needed to run the application, like code, runtime, libraries, and system tools. It eases development allowing to simulate a real world multi services scenario.                                                                                                                                                                                                                                                           |
| ![Grafana](README.files/Logo-Grafana.png)              | [Grafana](https://grafana.com/docs/grafana/latest/introduction/)         | Grafana enables to query, visualize, alert on, and explore metrics, logs, and traces provided by a system wherever they are stored using tools to turn time-series database (TSDB) data into graphs and visualisations. In the context of this study, is the responsible for displaying logs, metrics and tracing information with dashboards accessible by the browser and it is containerized using Docker technology.                                                                                                                       |
| ![Grafana Loki](README.files/Logo-Grafana-Loki.png)    | [Grafana Loki](https://grafana.com/docs/loki/)                           | A log aggregation system. It does not index the contents of the logs, only metadata about the logs’ labels. Log data itself is then compressed and stored in chunks in object stores. In the context of this study, it's containerized using Docker technology and receives the data from the OpenTelemetry Collector.                                                                                                                                                                                                                         |
| ![Grafana Tempo](README.files/Logo-Grafana-Tempo.png)  | [Grafana Tempo](https://grafana.com/docs/tempo/)                         | A tracing backend that ingests common open source tracing protocols, including OpenTelemetry. It allows to search for traces, generate metrics from spans, and link tracing data with logs and metrics. In the context of this study, it's containerized using Docker technology and receives the data from the OpenTelemetry Collector.                                                                                                                                                                                                       |
| ![Java](README.files/Logo-Java.png)                    | [Java Application](https://www.java.com/en/)                             | Java applications are software programs developed using the Java programming language and running in a virtual machine. In the context of this study there are three different types of Java applications, two of them are services that communicate between each other through messages, and the third one is the OpenTelemetry [Java Agent](https://opentelemetry.io/docs/zero-code/java/agent/) that collects and export telemetry data.                                                                                                    |
| ![Open Telemetry](README.files/Logo-OpenTelemetry.png) | [OpenTelemetry](https://opentelemetry.io/docs/what-is-opentelemetry/)    | An observability framework and toolkit designed to facilitate the generation, export, and collection of telemetry data such as traces, metrics, and logs. For Java developers it is a set of observability tools that consist of the API, the SDK, and instrumentation. It includes a [Java Agent](https://opentelemetry.io/docs/zero-code/java/agent/) that allows Zero-code instrumentation with a JAR attached to any Java 8+ application. It dynamically injects bytecode to capture telemetry from many popular libraries and frameworks. |
| ![Prometheus](README.files/Logo-Prometheus.png)        | [Prometheus](https://prometheus.io/docs/introduction/overview/)          | It is a systems monitoring and alerting toolkit that collects and stores metrics as time series data                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| ![Service](README.files/Logo-Service.png)              | Service Application                                                      | A smaller, independent application from a large ecosystem of applications that (usually) communicate with each other. Each service is responsible for a specific business function.                                                                                                                                                                                                                                                                                                                                                            |
| ![Spring-Boot](README.files/Logo-Spring-Boot.png)      | Spring Boot Powered Application                                          | TBD                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |

#### Architecture Elements

| #  | Image                                                                                     | Name                            | Description | 
|----|-------------------------------------------------------------------------------------------|---------------------------------|-------------|
| 1  | ![Spring Powered Service](README.files/Icon-Spring-Powered-Service.png)                   | Spring Powered Service          | TBD         |
| 2  | ![Plain Java Service](README.files/Icon-Plain-Java-Service.png)                           | Plain Java Service              | TBD         |
| 3  | ![Plain Java Service w/ OTEL SDK](README.files/Icon-Plain-Java-Service-OTEL-SDK.png)      | Plain Java Service w/ OTEL SDK  | TBD         |
| 4  | ![OTEL Java Agent](README.files/Icon-OTEL-Java-Agent.png)                                 | OTEL Java Agent                 | TBD         |
| 5  | ![Pulsar Docker Container](README.files/Icon-Pulsar-Docker-Container.png)                 | Pulsar Docker Container         | TBD         |
| 6  | ![Pulsar Topic](README.files/Icon-Pulsar-Topic.png)                                       | Pulsar Topic                    | TBD         |
| 7  | ![Pulsar Topic Offset](README.files/Icon-Pulsar-Topic-Offsets.png)                        | Pulsar Topic Offset             | TBD         |
| 8  | ![Pulsar Outgoing Message](README.files/Icon-Pulsar-Outgoing-Message.png)                 | Pulsar Outgoing Message         | TBD         |
| 9  | ![Pulsar Incoming Message](README.files/Icon-Pulsar-Incoming-Message.png)                 | Pulsar Incoming Message         | TBD         |
| 10 | ![OTEL Collector Docker Container](README.files/Icon-OTEL-Collector-Docker-Container.png) | OTEL Collector Docker Container | TBD         |
| 11 | ![Grafana Loki Docker Container](README.files/Icon-Grafana-Loki-Docker-Container.png)     | Grafana Loki Docker Container   | TBD         |
| 12 | ![Grafana Tempo Docker Container](README.files/Icon-Grafana-Tempo-Docker-Container.png)   | Grafana Tempo Docker Container  | TBD         |
| 13 | ![Prometheus Docker Container](README.files/Icon-Prometheus-Docker-Container.png)         | Prometheus Docker Container     | TBD         |
| 14 | ![Grafana Docker Container](README.files/Icon-Grafana-Docker-Container.png)               | Grafana Docker Container        | TBD         |

#### Flow

_TBD (Service 1 -> Service 2 -> ...)_

### What have I already achieved?

- Producing and consuming messages to/from a pulsar topic
  - In a Spring Boot Application
  - In a simple Java Application
- Tracing using OpenTelemetry and OTEL Collector
  - In a Spring Boot Application w/ OTEL Java Agent
  - In a simple Java Application w/ OTEL Java Agent
  - In a simple Java Application w/ OTEL SDK
- Logging using OpenTelemetry and OTEL Collector
  - In a Spring Boot Application w/ OTEL Java Agent
  - In a simple Java Application w/ OTEL Java Agent
- Metrics using OpenTelemetry and OTEL Collector
  - In a Spring Boot Application w/ OTEL Java Agent
  - In a simple Java Application w/ OTEL Java Agent
- Grafana through Tempo
  - From a Spring Boot Application w/ OTEL Java Agent
  - From a simple Java Application w/ OTEL Java Agent
  - From a simple Java Application w/ OTEL SDK
- Grafana through Loki
  - From a Spring Boot Application w/ OTEL Java Agent
  - From a simple Java Application w/ OTEL Java Agent
- Grafana through Prometheus
  - From a Spring Boot Application w/ OTEL Java Agent
  - From a simple Java Application w/ OTEL Java Agent

## Pulsar references:

- [Pulsar Java client](https://pulsar.apache.org/docs/next/client-libraries-java/)
  - [Set up Java client](https://pulsar.apache.org/docs/next/client-libraries-java-setup/) (dependencies)
  - [Initialize](https://pulsar.apache.org/docs/next/client-libraries-java-initialize/) (Pulsar Client & service URL)
  - [Use a Java client](https://pulsar.apache.org/docs/next/client-libraries-java-use/) (Producer, Consumer & Message Listener)
- [Spring Boot & Apache Pulsar](https://docs.spring.io/spring-pulsar/docs/1.2.4/reference/reference/pulsar/quick-tour.html) (Quick Tour)

## OpenTelemetry references:

- [OpenTelemetry Java Examples](https://github.com/open-telemetry/opentelemetry-java-examples/)
- [Java Agent](https://opentelemetry.io/docs/zero-code/java/agent/) (Zero-code instrumentation)
  - [Annotations](https://opentelemetry.io/docs/zero-code/java/agent/annotations/) (@WithSpan, @SpanAttribute)
- [Spring Boot starter](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/)
  - [Getting started](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/getting-started/) (Maven Dependencies)
  - [Annotations](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/annotations/) (@WithSpan)
- [Collector](https://opentelemetry.io/docs/collector/)
  - [Install the Collector](https://opentelemetry.io/docs/collector/installation/) (Docker Compose)
  - [Collector Configuration](https://opentelemetry.io/docs/collector/configuration/) (not needed, use default)

## Logback references

- [Layouts](https://logback.qos.ch/manual/layouts.html) (Pattern Layout)

## Docker references

It seems to be a complete guide to put the entire ecosystem working!

- [A practical guide to implement OpenTelemetry in Spring Boot](https://vorozco.com/blog/2024/2024-11-18-A-practical-guide-spring-boot-open-telemetry.html)

## Next Steps:

### Yet to achieve:

- Logging using OpenTelemetry and OTEL Collector
  - In a simple Java Application w/ OTEL SDK
- Metrics using OpenTelemetry and OTEL Collector
  - In a simple Java Application w/ OTEL SDK
- Grafana through Loki
  - In a simple Java Application w/ OTEL SDK

### Use Grafana Alloy instead of OTEL Collector

Grafana already has its own solution for collecting logs, traces and metrics called Grafana Alloy. This is the "de 
facto" real-world solution used.

### Split a message into two or more. All of them must have the same trace ID

If a consumed message must be transformed in two or more and using multithreading publish to other Pulsar topic(s), all 
messages must keep the same trace ID.

### Keep applications alive

Find a way to start and keep alive applications even though Pulsar is not available