package com.example.board.kafka

import com.example.board.kafka.dto.TestDto
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BoardKafkaPublisher(private val kafkaTemplate: KafkaTemplate<String, TestDto>) : Loggable() {
    private val kafkaTopic = "board-topic3"
    private val log = KotlinLogging.logger {}
    fun sendMessage(dto: TestDto) {
        kafkaTemplate.send(kafkaTopic, dto) // 그냥 메시지 전송
        log.info("Kafka 발행 완료: $dto")
    }

}