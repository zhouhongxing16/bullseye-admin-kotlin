package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.response.DictionaryTypeDataResponse
import com.chris.bullseye.system.mapper.DictionaryTypeDataMapper
import com.chris.bullseye.system.pojo.DictionaryTypeData
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 16:02
 */

@Service
class DictionaryTypeDataService(var dictionaryTypeDataMapper: DictionaryTypeDataMapper): BaseService<DictionaryTypeData>() {
    override fun getMapper(): MPBaseMapper<DictionaryTypeData> {
        return dictionaryTypeDataMapper
    }
    fun getDtoListByParams(params: Map<String, String?>?): List<DictionaryTypeDataResponse>? {
        return dictionaryTypeDataMapper.getDtoListByParams(params)
    }

}