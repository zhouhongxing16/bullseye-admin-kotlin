package com.chris.bullseye.basemapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper

/**
 * @author  Chris
 * @date 2021-10-21 14:24
 */
interface  MPBaseMapper<T> : BaseMapper<T> {
    fun getListByParams(map: MutableMap<String, String?>): List<T>

    fun getByParams(map: MutableMap<String, String?>): T
}