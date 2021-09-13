package com.chris.bullseye.entity.response

import com.chris.bullseye.pojo.Organization

/**
 * @author Chris
 * @date 2021-01-08 16:18
 */
data class LoginRequest(
        var username: String? = null,

        var password: String? = null,

        var type: Int? = 0,

        var organizationId: String? = null,
)