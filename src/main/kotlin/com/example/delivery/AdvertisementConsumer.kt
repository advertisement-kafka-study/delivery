package com.example.delivery

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AdvertisementConsumer(val objectMapper: ObjectMapper) {

    private final val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consumer(record: ConsumerRecord<String, String>) {
        val cloudEvent = JSONObject(record.value())
        val advertisement = objectMapper.readValue(cloudEvent.get("data")
                .toString(), Advertisement::class.java)
        log.info("Consuming Message=$advertisement")
    }

}