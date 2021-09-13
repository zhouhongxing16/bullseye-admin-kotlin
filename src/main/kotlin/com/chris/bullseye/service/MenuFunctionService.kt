package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.MenuFunctionMapper
import com.chris.bullseye.pojo.MenuFunction
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-11 17:18
 */
@Service
class MenuFunctionService(var menuFunctionMapper: MenuFunctionMapper):BaseService<MenuFunction>() {
    override fun getMapper(): BaseMapper<MenuFunction> {
        return menuFunctionMapper
    }
}