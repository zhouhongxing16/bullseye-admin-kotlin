package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.pojo.RoleMenu
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 15:58
 */

@Mapper
interface RoleMenuMapper: BaseMapper<RoleMenu> {
    fun deleteByParams(params: Map<String, String?>): Int
}