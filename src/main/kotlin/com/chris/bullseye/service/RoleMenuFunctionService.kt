package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.RoleMenuFunctionMapper
import com.chris.bullseye.mapper.RoleMenuMapper
import com.chris.bullseye.pojo.RoleMenu
import com.chris.bullseye.pojo.RoleMenuFunction
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