package com.nuwe.challenge.computerwoman.config

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.shell.jline.PromptProvider





@Configuration
class AppConfig {
    @Bean
    @Scope("prototype")
    fun produceLogger(injectionPoint: InjectionPoint): Logger? {
        val classOnWired = injectionPoint.member.declaringClass
        return LoggerFactory.getLogger(classOnWired)
    }

    @Bean
    fun promptProvider(): PromptProvider? {
        return PromptProvider {
            AttributedString(
                "ballisticShell=>",
                AttributedStyle.DEFAULT
            )
        }
    }
}