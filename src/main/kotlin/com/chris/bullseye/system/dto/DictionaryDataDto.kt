package com.chris.bullseye.system.dto

import com.chris.bullseye.system.pojo.DictionaryData

/**
 * @author Chris
 * @date 2020 12 08 11:59
 */
data class DictionaryDataDto(

        var typeName: String? = null,

        val userName: String? = null

): DictionaryData()
