package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.DictionaryType
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 16:01
 */

@Mapper
interface DictionaryTypeMapper : MPBaseMapper<DictionaryType> {
}