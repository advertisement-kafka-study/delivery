# Overview

This is a repository represents the Delivery at the Advertisement Architecture.

# Architecture

![alt text](https://raw.githubusercontent.com/advertisement-kafka-study/delivery/master/advertisement.png "Architecture")

## Schema

All the schemas are found in `/apache-avro-producer/src/main/avro`

## Setup

To start the Kafka Stack, first use need to download the [kafka-stack-docker-compose](https://github.com/advertisement-kafka-study/kafka-stack-docker-compose)

After that, run the docker-compose:

`docker-compose -f full-stack.yml up`

# Stack

[Kotlin](https://kotlinlang.org/)

[Spring Boot](https://spring.io/projects/spring-boot)

[Apache Kafka](https://kafka.apache.org/)

[Apache Avro](https://avro.apache.org/)

# References

[Guide to Spring Cloud Stream with Kafka, Apache Avro and Confluent Schema Registry](https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent)