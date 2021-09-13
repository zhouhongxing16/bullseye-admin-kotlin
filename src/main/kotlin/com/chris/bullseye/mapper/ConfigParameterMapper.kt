package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.ConfigParameter
import org.apache.ibatis.annotations.Mapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
@Mapper
interface ConfigParameterMapper: BaseMapper<ConfigParameter> {
}