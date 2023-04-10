package com.waitring.waitring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableJpaAuditing
class WaitRingApplication

fun main(args: Array<String>) {
    runApplication<WaitRingApplication>(*args)
}