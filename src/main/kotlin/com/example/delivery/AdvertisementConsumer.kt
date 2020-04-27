package com.example.delivery

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AdvertisementConsumer(val objectMapper: ObjectMapper, val restTemplate: RestTemplate) {

    private final val log = LoggerFactory.getLogger(this::class.java)

    private final val relations = mutableMapOf<String, String>()

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consumer(record: ConsumerRecord<String, String>) {
        val key = record.key()
        val cloudEvent = JSONObject(record.value())
        val advertisement = objectMapper.readValue(cloudEvent.get("data")
                .toString(), Advertisement::class.java)

        when (relations[advertisement.id]) {
            null -> {
                relations[advertisement.id] = key
                process(key, advertisement)
            }
            key -> {
                process(key, advertisement)
            }
            else -> {
                log.warn("Advertisement=[$key] Customer=[${advertisement.id}] lost the bid")
            }
        }
    }

    @Retryable
    fun process(key: String, advertisement: Advertisement) {
        log.info("Advertisement=[$key] Customer=[${advertisement.id}] won the bid")
        val url = advertisement.callback
        log.info("Calling $url")
        // FIXME Definir Body
        val responseEntity = restTemplate.postForEntity(url, null, Void::class.java)
        log.info("Called $url status ${responseEntity.statusCode}")
    }

}