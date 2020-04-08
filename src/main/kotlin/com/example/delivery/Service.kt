package com.example.delivery

import io.cloudevents.v03.CloudEventImpl
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class Service {

    private final val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consumer(record: ConsumerRecord<String, CloudEventImpl<Advertisement>>) {
        log.info("Consuming Message=[${record.value().data}] Key=[${record.key()}]")
    }

}