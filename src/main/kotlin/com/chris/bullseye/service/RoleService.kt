package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.RoleMapper
import com.chris.bullseye.pojo.Role
import org.springframework.stereotype.Service


/**
 * @author Chris
 * @date 2020 12 07 17:12
 */
@Service
class RoleService(var roleMapper: RoleMapper) :BaseService<Role>() {
    override fun getMapper(): BaseMapper<Role> {
        return roleMapper
    }


    fun getRolesByAccountId(accountId: String?): List<Role> {
        return roleMapper.getRolesByAccountId(accountId)
    }
}