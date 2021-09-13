package com.chris.bullseye.dto

import com.chris.bullseye.pojo.RoleMenu

/**
 * @author Chris
 * @date 2020 12 08 13:43
 */
data class RoleMenuDto(
        var menuName: String? = null,

        val menuIcon: String? = null,

        val menuPath: String? = null,

        val menuSort: String? = null
) : RoleMenu()
