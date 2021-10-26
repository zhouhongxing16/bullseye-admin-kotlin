package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.DictionaryDataDto
import com.chris.bullseye.system.pojo.DictionaryData
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 16:00
 */
@Mapper
interface DictionaryDataMapper : MPBaseMapper<DictionaryData> {
    fun getDtoListByParams(map: Map<String, String?>?): List<DictionaryDataDto>?
}