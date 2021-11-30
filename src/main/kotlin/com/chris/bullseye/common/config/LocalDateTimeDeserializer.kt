package com.chris.bullseye.common.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDateTime
import java.time.ZoneOffset


/**
 * @author Chris
 * @date 2021-11-30 17:35
 */
class LocalDateTimeDeserializer: JsonDeserializer<LocalDateTime>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): LocalDateTime {
        val timestamp: Long = p0!!.longValue
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8))
    }
}