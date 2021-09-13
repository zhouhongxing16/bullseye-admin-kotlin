package com.chris.bullseye.entity

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

        var url: String? = null,

        var code: String? = null,

        var status: Int? = null,

        var data: T? = null,

        var list: List<T>? = null,

        var page: PageResult<T>? = null
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

    fun success(data: T?, msg: String): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = true
        result.message = msg
        result.data = data
        result.status = HttpStatus.OK.value()
        return result
    }

    fun successList(list: List<T>, msg: String?): JsonResult<T> {
        var result = JsonResult<T>()
        result.success = true
        result.message = if (msg.isNullOrEmpty()) "查询成功" else msg
        result.list = list
        result.status = HttpStatus.OK.value()
        return result
    }

    fun successPageList(list: List<T>?, total: Long, isLastPage: Boolean, msg: String?): JsonResult<T> {
        var result = JsonResult<T>()
        var page = PageResult<T>()
        page.total = total
        page.list = list
        page.isLastPage = isLastPage
        result.success = true
        result.message = msg
        result.page = page
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

        fun success(data: Any, msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = true
            result.message = msg
            result.data = data
            result.status = HttpStatus.OK.value()
            return result
        }

        fun successList(list: List<Any>, msg: String): JsonResult<Any> {
            var result = JsonResult<Any>()
            result.success = true
            result.message = msg
            result.list = list
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

data class PageResult<T>(
        var total: Long? = null,

        var list: List<T>? = null,

        var isLastPage: Boolean = false
)
