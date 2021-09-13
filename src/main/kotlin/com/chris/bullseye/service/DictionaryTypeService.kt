package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.DictionaryDataMapper
import com.chris.bullseye.mapper.DictionaryTypeMapper
import com.chris.bullseye.pojo.DictionaryType
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 16:04
 */

@Service
class DictionaryTypeService(var dictionaryTypeMapper: DictionaryTypeMapper) : BaseService<DictionaryType>() {
    override fun getMapper(): BaseMapper<DictionaryType> {
        return dictionaryTypeMapper
    }
}