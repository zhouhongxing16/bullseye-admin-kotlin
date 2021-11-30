package com.chris.bullseye.common.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * @author Chris
 * @date 2021-11-30 17:32
 */
class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {
    override fun serialize(p0: LocalDateTime?, p1: JsonGenerator?, p2: SerializerProvider?) {
        p1!!.writeNumber(p0!!.toInstant(ZoneOffset.ofHours(8)).toEpochMilli())
    }

}