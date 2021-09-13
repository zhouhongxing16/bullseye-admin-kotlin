package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.system.pojo.Banner
import com.chris.bullseye.system.mapper.BannerMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:24
 */
@Service
class BannerService(var bannerMapper: BannerMapper):BaseService<Banner>() {

    override fun getMapper(): BaseMapper<Banner> {
        return bannerMapper
    }
}