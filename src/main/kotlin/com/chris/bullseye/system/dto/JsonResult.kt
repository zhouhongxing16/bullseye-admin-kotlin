package com.chris.bullseye.system.dto

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

        var data:Any? = null,

        var list:List<T>? = null,

        var time: String? = DateUtils.getCurrentTime()
):Serializable {
    fun failed(msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = false
        result.message = msg
        result.status = HttpStatus.OK.value()
        result.time =  DateUtils.getCurrentTime()
        return result
    }

    fun failed(msg: String,status: Int = HttpStatus.OK.value()): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = false
        result.message = msg
        result.status = status
        result.time =  DateUtils.getCurrentTime()
        return result
    }

    fun success(data: Any?, msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = true
        result.message = msg
        result.data = data
        result.status = HttpStatus.OK.value()
        result.time =  DateUtils.getCurrentTime()
        return result
    }

    fun success(list: List<T>?, msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = true
        result.message = msg
        result.list = list
        result.status = HttpStatus.OK.value()
        result.time =  DateUtils.getCurrentTime()
        return result
    }

    companion object {
        fun failed(msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = false
            result.message = msg
            result.time =  DateUtils.getCurrentTime()
            return result
        }

        fun failed(msg: String,status: Int = HttpStatus.OK.value()): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = false
            result.message = msg
            result.status = status
            result.time =  DateUtils.getCurrentTime()
            return result
        }

        fun success(data: Any?, msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = true
            result.message = msg
            result.data = data
            result.status = HttpStatus.OK.value()
            result.time =  DateUtils.getCurrentTime()
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
