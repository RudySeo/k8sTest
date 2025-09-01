package com.example.board.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Hello {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, Prometheus + Grafana!"
    }
}