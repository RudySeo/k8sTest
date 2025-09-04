package com.example.board.kafka

import com.example.board.kafka.dto.TestDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BoardKafkaPublisher(private val kafkaTemplate: KafkaTemplate<String, TestDto>) {
    private val logger = LoggerFactory.getLogger(BoardKafkaPublisher::class.java)
    private val kafkaTopic = "board-topic3"

    fun sendMessage(dto: TestDto) {
        kafkaTemplate.send(kafkaTopic, dto) // 그냥 메시지 전송
        logger.info("Kafka 발행 완료: $dto")
    }

}