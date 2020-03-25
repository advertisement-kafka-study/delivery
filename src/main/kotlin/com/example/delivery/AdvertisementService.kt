package com.example.delivery

import com.example.advertisement.Advertisement
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AdvertisementService() {

    private final val log = LoggerFactory.getLogger(this::class.java)

    private var advertisementKey: String? = null

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consumer(record: ConsumerRecord<String, Advertisement>) {
        if (advertisementKey == null) {
            this.advertisementKey = record.key();
        }

        if (this.advertisementKey == record.key()) {
            log.info("Consuming Key=[$advertisementKey] Message=[${record.value()}]")
        }
    }

}