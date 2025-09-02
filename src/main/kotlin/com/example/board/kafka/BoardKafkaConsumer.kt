package com.example.board.kafka

import com.example.board.kafka.dto.TestDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class BoardKafkaConsumer {
    private val logger = LoggerFactory.getLogger(BoardKafkaConsumer::class.java)

    @KafkaListener(topics = ["board-topic"], groupId = "board-group")
    fun consume(dto: TestDto) {
        logger.info("Kafka 메시지 수신: $dto")
    }
}