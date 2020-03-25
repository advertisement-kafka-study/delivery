package com.example.globo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ApacheAvroApplication

fun main(args: Array<String>) {
	runApplication<ApacheAvroApplication>(*args)
}
