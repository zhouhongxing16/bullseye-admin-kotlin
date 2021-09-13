package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.RoleMenuFunction
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 15:58
 */

@Mapper
interface RoleMenuFunctionMapper: BaseMapper<RoleMenuFunction> {

    fun getRoleAuthPaths(): List<Map<String, String?>>

    fun getAuthByMenuAndRoleId(param: Map<String?, String?>?): List<Map<String?, String?>?>?

    fun deleteByParams(params: Map<String?, Any?>?): Int
}