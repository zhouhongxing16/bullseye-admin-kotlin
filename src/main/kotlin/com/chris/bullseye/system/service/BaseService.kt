package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.JsonResult
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * @author Chris
 *
 *
 * @date2020 12 01 14:46
 */
abstract class BaseService<T> {

    abstract fun getMapper(): MPBaseMapper<T>

    @Transactional(propagation = Propagation.REQUIRED)
    open fun add(obj: T?): JsonResult<T> {
        var result = JsonResult<T>()
        var status = getMapper().insert(obj)
        return if (status>0) {
            result.success(obj, "操作成功！")
        } else {
            result.failed("操作失败！")
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    open fun update(obj: T?): Int {
        return getMapper().updateById(obj)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    open fun deleteById(id: String?): Int {
        return getMapper().deleteById(id)
    }

    fun getById(hId: String?): T {
        return getMapper().selectById(hId)
    }

    fun getListByParams(params: MutableMap<String,String?>): List<T> {
        return getMapper().getListByParams(params)
    }

    fun getByParams(params: MutableMap<String,String?>): T {
        return getMapper().getByParams(params)
    }

    fun selectByMap(params: Map<String, String?>): List<T> {
        return getMapper().selectByMap(params)
    }
}
