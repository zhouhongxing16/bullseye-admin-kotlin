package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.Role
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Chris
 * @date2020 12 07 16:58
 */
@Mapper
interface RoleMapper : BaseMapper<Role> {

    fun getRolesByAccountId(@Param("accountId") accountId: String?): List<Role>

}