package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.pojo.MenuFunction
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-11 17:18
 */

@Mapper
interface MenuFunctionMapper :BaseMapper<MenuFunction> {
}