package com.example.board.kafka

import com.example.board.kafka.dto.TestDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BoardKafkaPublisher(private val kafkaTemplate: KafkaTemplate<String, TestDto>) {
    private val logger = LoggerFactory.getLogger(BoardKafkaPublisher::class.java)

    fun sendMessage(dto: TestDto) {
        kafkaTemplate.send("board-topic", dto) // 그냥 메시지 전송
        logger.info("Kafka 발행 완료: $dto")
    }

}