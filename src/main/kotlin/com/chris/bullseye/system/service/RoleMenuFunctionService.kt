package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.mapper.RoleMenuFunctionMapper
import com.chris.bullseye.system.pojo.RoleMenuFunction
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 15:57
 */
@Service
class RoleMenuFunctionService(var roleMenuFunctionMapper: RoleMenuFunctionMapper) :BaseService<RoleMenuFunction>() {

    override fun getMapper(): BaseMapper<RoleMenuFunction> {
        return roleMenuFunctionMapper
    }
}