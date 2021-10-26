package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.RoleMenu
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 15:58
 */

@Mapper
interface RoleMenuMapper: MPBaseMapper<RoleMenu> {
    fun deleteByParams(params: Map<String, String?>): Int
}