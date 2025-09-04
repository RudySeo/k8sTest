package com.example.board.kafka

import com.example.board.Board
import com.example.board.BoardRepository
import com.example.board.kafka.dto.TestDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class BoardKafkaConsumer(private val boardRepository: BoardRepository) : Loggable() {

    @KafkaListener(topics = ["board-topic3"])
    fun consume(dto: TestDto) {
        logger.info("Kafka 메시지 수신: $dto")
        val saver = Board(title = dto.title, content = dto.content)
        boardRepository.save(saver)

        logger.info("Kafka 메시지 수신완료: $saver")
    }
}
