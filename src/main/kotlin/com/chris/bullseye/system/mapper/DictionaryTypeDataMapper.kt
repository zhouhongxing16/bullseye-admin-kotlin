package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.response.DictionaryTypeDataResponse
import com.chris.bullseye.system.pojo.DictionaryTypeData
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-10 16:00
 */
@Mapper
interface DictionaryTypeDataMapper : MPBaseMapper<DictionaryTypeData> {
    fun getDtoListByParams(map: Map<String, String?>?): List<DictionaryTypeDataResponse>?
}