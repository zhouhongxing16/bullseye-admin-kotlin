package com.chris.bullseye.basemapper

import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.MySqlMapper

/**
 * @author  Chris
 * @date2020 12 01 14:24
 */
interface  BaseMapper<T> : Mapper<T>, MySqlMapper<T> {
    fun getListByParams(map: MutableMap<String, String?>): List<T>

    fun getByParams(map: MutableMap<String, String?>): T
}