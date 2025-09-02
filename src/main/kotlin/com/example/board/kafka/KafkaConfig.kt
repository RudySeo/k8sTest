package com.example.board.kafka

import com.example.board.kafka.dto.TestDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConfig {
    // Producer 설정
    @Bean
    fun producerFactory(): ProducerFactory<String, TestDto> {
        val config = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to org.apache.kafka.common.serialization.StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to org.springframework.kafka.support.serializer.JsonSerializer::class.java
        )
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, TestDto> {
        return KafkaTemplate(producerFactory())
    }

    // Consumer 설정
    @Bean
    fun consumerFactory(): ConsumerFactory<String, TestDto> {
        val deserializer = JsonDeserializer(TestDto::class.java).apply {
            addTrustedPackages("*")   // 모든 패키지 허용
            setUseTypeMapperForKey(true)
        }

        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ConsumerConfig.GROUP_ID_CONFIG to "board-group",  // listener와 동일
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to org.apache.kafka.common.serialization.StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer::class.java
        )

        return DefaultKafkaConsumerFactory(
            config,
            org.apache.kafka.common.serialization.StringDeserializer(),
            deserializer
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TestDto> {
        return ConcurrentKafkaListenerContainerFactory<String, TestDto>().apply {
            consumerFactory = consumerFactory()
        }
    }
}