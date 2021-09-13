package com.chris.bullseye.dto

import com.chris.bullseye.pojo.Staff

/**
 * @author Chris
 * @date 2020 12 08 13:38
 */
data class StaffDto(
        var genderName: String? = null,

        val titleName: String? = null,

        val majorName: String? = null,

        val userName: String? = null,

        val addAccountFlag: Boolean = false,
):Staff()
