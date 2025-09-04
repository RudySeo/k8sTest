package com.example.board.kafka

open class Loggable {
    protected val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)
}