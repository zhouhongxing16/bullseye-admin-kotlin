package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.mapper.DictionaryTypeMapper
import com.chris.bullseye.system.pojo.DictionaryType
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