package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.dto.DictionaryDataDto
import com.chris.bullseye.system.mapper.DictionaryDataMapper
import com.chris.bullseye.system.pojo.DictionaryData
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 16:02
 */

@Service
class DictionaryDataService(var dictionaryDataMapper: DictionaryDataMapper): BaseService<DictionaryData>() {
    override fun getMapper(): BaseMapper<DictionaryData> {
        return dictionaryDataMapper
    }
    fun getDtoListByParams(params: Map<String, String?>?): List<DictionaryDataDto>? {
        return dictionaryDataMapper.getDtoListByParams(params)
    }

}