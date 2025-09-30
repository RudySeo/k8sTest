package com.example.board

import com.example.board.kafka.BoardKafkaPublisher
import com.example.board.kafka.dto.TestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BoardController(
    private val boardService: BoardService,
    private val publisher: BoardKafkaPublisher
) {

    @PostMapping("/board")
    fun createBoard(@RequestBody dto: TestDto): ResponseEntity<String> {
        publisher.sendMessage(dto)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Board 생성 요청이 Kafka에 전달되었습니다.")
    }

    //템플릿테스트
    @PutMapping("/board/{id}")
    fun updateBoard(
        @PathVariable id: Long,
        request: BoardDto.BoardRequest
    ): ResponseEntity<BoardDto.BoardResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(id, request))
    }

    @DeleteMapping("/board/{id}")
    fun deleteBoard(@PathVariable id: Long): ResponseEntity<BoardDto.BoardResponse> {
        boardService.deleteBoard(id)

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/board")
    fun getBoards(): ResponseEntity<List<BoardDto.BoardResponse>> {
        val responseList = boardService.getBoards()
        return ResponseEntity.ok(responseList)
    }

    @GetMapping("/board/{id}")
    fun getBoard(@PathVariable id: Long): ResponseEntity<BoardDto.BoardResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(id))
    }

}