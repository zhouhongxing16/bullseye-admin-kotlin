package com.chris.bullseye.system.entity

import com.chris.bullseye.system.pojo.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * @author Chris
 * @date2020 12 01 17:21
 */
class User : User {

    constructor(
            id: String?,
            username: String?,
            password: String?,
            organizationId: String?,
            staffId: String?,
            departmentId: String?,
            accountLocked: Boolean?,
            accountExpired: Boolean?,
            authorities: MutableCollection<out GrantedAuthority?>?) : super(username, password, authorities) {

        this.id = id
        this.organizationId = organizationId
        this.staffId = staffId
        this.departmentId = departmentId
        this.accountLocked = accountLocked
        this.accountExpired = accountExpired
    }

    constructor(username: String?, password: String?, authorities: Collection<GrantedAuthority>) : super(username, password, authorities) {

    }

    var id: String? = null

    var organizationId: String? = null

    var staffId: String? = null

    var departmentId: String? = null

    //账户是否锁定
    var accountLocked: Boolean? = false

    //账户是否过期
    var accountExpired: Boolean? = false

    var roles: List<Role>? = null

    val mobile: String? = null

    val captcha: String? = null

    val remember: String? = null

    var platform: String? = null

    var loginType: String? = null

    /**
     * 当前角色
     */
    var currentRole: Role? = null

}