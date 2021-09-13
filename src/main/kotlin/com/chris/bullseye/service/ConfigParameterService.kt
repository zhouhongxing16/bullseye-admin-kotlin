package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.pojo.ConfigParameter
import com.chris.bullseye.mapper.ConfigParameterMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
@Service
class ConfigParameterService(var configParameterMapper: ConfigParameterMapper):BaseService<ConfigParameter>() {

    override fun getMapper(): BaseMapper<ConfigParameter> {
        return configParameterMapper
    }
}