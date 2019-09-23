package com.example.userlogin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import kotlin.Exception

@SpringBootApplication
class UserloginApplication: SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(UserloginApplication::class.java)
    }


}

@Throws(Exception::class)
fun main(args: Array<String>) {
    runApplication<UserloginApplication>(*args)
}


