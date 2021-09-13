package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.system.pojo.Navigation
import com.chris.bullseye.system.mapper.NavigationMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 */
@Service
class NavigationService(var navigationMapper: NavigationMapper):BaseService<Navigation>() {

    override fun getMapper(): BaseMapper<Navigation> {
        return navigationMapper
    }
}