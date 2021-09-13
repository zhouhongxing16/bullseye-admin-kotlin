package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.dto.DictionaryDataDto
import com.chris.bullseye.pojo.DictionaryData
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 16:00
 */
@Mapper
interface DictionaryDataMapper : BaseMapper<DictionaryData> {
    fun getDtoListByParams(map: Map<String, String?>?): List<DictionaryDataDto>?
}