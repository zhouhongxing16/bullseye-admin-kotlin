package com.chris.bullseye.system.entity

import com.chris.bullseye.common.utils.DateUtils
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.io.Serializable

/**
 * @author Chris
 * @date2020 12 01 14:43
 */
@Component
data class JsonResult<T>(
        var success: Boolean = false,

        var message: String? = null,

        var code: String? = null,

        var status: Int? = null,

        var data: Any? = null,

        var time: String? = DateUtils.getCurrentTime()
):Serializable {
    fun failed(msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = false
        result.message = msg
        result.status = HttpStatus.OK.value()
        return result
    }

    fun failed(msg: String,status: Int = HttpStatus.OK.value()): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = false
        result.message = msg
        result.status = status
        return result
    }

    fun success(data: Any?, msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = true
        result.message = msg
        result.data = data
        result.status = HttpStatus.OK.value()
        return result
    }

    companion object {
        fun failed(msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = false
            result.message = msg
            return result
        }

        fun failed(msg: String,status: Int = HttpStatus.OK.value()): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = false
            result.message = msg
            result.status = status
            return result
        }

        fun success(data: Any?, msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = true
            result.message = msg
            result.data = data
            result.status = HttpStatus.OK.value()
            return result
        }
    }

    override fun toString(): String {
        return try {
            ObjectMapper().writeValueAsString(this)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            "{}"
        }
    }


}
