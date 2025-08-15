package com.example.board

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BoardService(
    private val boardRepository: BoardRepository,
) {

    fun createBoard(request: BoardDto.BoardRequest): BoardDto.BoardResponse {

        val entity = Board(
            title = request.title,
            content = request.content
        )

        val saved = boardRepository.save(entity)

        return BoardDto.BoardResponse.from(saved)
    }

    fun updateBoard(id: Long, request: BoardDto.BoardRequest): BoardDto.BoardResponse {

        val board = boardRepository.findById(id).orElseThrow() {
            throw NotFoundException()
        }

        board.title = request.title
        board.content = request.content

        val saved = boardRepository.save(board)
        return BoardDto.BoardResponse.from(saved)

    }

    fun deleteBoard(id: Long) {

        val board = boardRepository.findById(id)
            .orElseThrow { NotFoundException() }
        return boardRepository.delete(board)
    }

    fun getBoards(): List<BoardDto.BoardResponse> {
        val boards = boardRepository.findAll()

        return boards.map { BoardDto.BoardResponse.from(it) }
    }

    fun getBoard(id: Long): BoardDto.BoardResponse {
        val board = boardRepository.findById(id).orElseThrow() {
            throw NotFoundException()
        }

        return BoardDto.BoardResponse.from(board)
    }


}
