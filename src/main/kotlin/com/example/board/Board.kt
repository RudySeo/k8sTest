package com.example.board

import jakarta.persistence.*

@Entity
data class Board(

    @Column(name = "board_id", nullable = true)
    var title: String,

    @Column(name = "content", nullable = true)
    var content: String,

    ) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}