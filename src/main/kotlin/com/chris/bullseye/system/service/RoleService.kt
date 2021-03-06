package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.mapper.RoleMapper
import com.chris.bullseye.system.pojo.Role
import org.springframework.stereotype.Service


/**
 * @author Chris
 * @date 2020 12 07 17:12
 */
@Service
class RoleService(var roleMapper: RoleMapper) :BaseService<Role>() {
    override fun getMapper(): MPBaseMapper<Role> {
        return roleMapper
    }


    fun getRolesByAccountId(accountId: String?): List<Role> {
        return roleMapper.getRolesByAccountId(accountId)
    }
}