package com.chris.bullseye.system.dto

import com.chris.bullseye.system.pojo.Account

/**
 * @author Chris
 * @date2020 12 07 10:53
 */
data class AccountDto(
        var organizationName:String? = null ,

        var departmentName:String? = null,

        var levelName:String? = null,

        var staffName:String? = null,
) : Account()