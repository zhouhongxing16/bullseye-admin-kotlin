package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.Role
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Chris
 * @date2020 12 07 16:58
 */
@Mapper
interface RoleMapper : MPBaseMapper<Role> {

    fun getRolesByAccountId(@Param("accountId") accountId: String?): List<Role>

}