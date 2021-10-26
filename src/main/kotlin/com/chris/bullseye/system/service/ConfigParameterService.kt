package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.system.pojo.ConfigParameter
import com.chris.bullseye.system.mapper.ConfigParameterMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
@Service
class ConfigParameterService(var configParameterMapper: ConfigParameterMapper):BaseService<ConfigParameter>() {

    override fun getMapper(): MPBaseMapper<ConfigParameter> {
        return configParameterMapper
    }
}