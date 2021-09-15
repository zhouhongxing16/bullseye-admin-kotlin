package com.chris.bullseye.common.config

import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * @author Chris
 * @date 2021-09-15 19:02
 */
@Configuration
class RedisConfiguration {
    @Autowired
    private val redisTemplate: RedisTemplate<*, *>? = null

    @Bean(name = ["redisTemplate"])
    fun redisTemplate(factory: RedisConnectionFactory?): RedisTemplate<String, String>? {
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(factory!!)
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        template.afterPropertiesSet()
        return template
    }
}