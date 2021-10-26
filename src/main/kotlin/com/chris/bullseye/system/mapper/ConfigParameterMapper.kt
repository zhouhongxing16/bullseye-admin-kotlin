package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.ConfigParameter
import org.apache.ibatis.annotations.Mapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
@Mapper
interface ConfigParameterMapper: MPBaseMapper<ConfigParameter> {
}