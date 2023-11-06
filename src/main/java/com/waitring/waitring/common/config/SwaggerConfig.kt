package com.waitring.waitring.common.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(info = Info(title = "Wait-Ring API", description = "원격 웨이팅 서비스 Wait-Ring API 명세서입니다", version = "v1.0.0"))
class SwaggerConfig {

    @Bean
    fun openApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
                .group("Wait-Ring API")
                .pathsToMatch("/**")
                .build()
    }
}