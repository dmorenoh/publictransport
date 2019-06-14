package com.myapps.publictransport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PublicTransportApplication

fun main(args: Array<String>) {
    runApplication<PublicTransportApplication>(*args)
}
