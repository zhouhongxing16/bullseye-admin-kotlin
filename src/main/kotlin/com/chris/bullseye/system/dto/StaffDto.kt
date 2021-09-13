package com.chris.bullseye.system.dto

import com.chris.bullseye.system.pojo.Staff

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
