package com.chris.bullseye.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Chris
 * @date 2021-01-13 11:13
 */
@Configuration
class WebConfiguration : WebMvcConfigurer {

    @Value("\${uploadConfig.uploadPath}")
    var uploadPath: String? = null

    @Value("\${uploadConfig.prefix}")
    var prefix: String? = null

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        //本地静态资源映射
        registry.addResourceHandler("$prefix/**").addResourceLocations("file:$uploadPath/")
    }
}