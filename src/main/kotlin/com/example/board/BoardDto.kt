package com.example.board

class BoardDto {

    data class BoardRequest(val title: String, val content: String)

    data class BoardResponse(
        val id: Long?,
        val title: String,
        val content: String,
    ) {
        companion object {
            fun from(board: Board) = BoardResponse(
                id = board.id,
                title = board.title,
                content = board.content
            )
        }
    }
}
