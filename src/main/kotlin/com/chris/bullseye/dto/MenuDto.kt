package com.chris.bullseye.dto

import com.chris.bullseye.pojo.Menu

/**
 * @author Chris
 * @date 2020 12 08 13:45
 */
data class MenuDto(
        private var open: Boolean = false,

        val value: String? = null,

        val key: String? = null,

        val type: String? = null,

        val isLeaf: Boolean = false,

        val childNum: Int = 0,

        var userName: String? = null,

        var children: List<MenuDto>? = null
) : Menu()
