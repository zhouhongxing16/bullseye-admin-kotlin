package com.chris.bullseye.dto

import com.chris.bullseye.pojo.DictionaryData

/**
 * @author Chris
 * @date 2020 12 08 11:59
 */
data class DictionaryDataDto(

        var typeName: String? = null,

        val userName: String? = null

): DictionaryData()
