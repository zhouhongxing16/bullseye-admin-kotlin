package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.DictionaryType
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 16:01
 */

@Mapper
interface DictionaryTypeMapper : BaseMapper<DictionaryType> {
}