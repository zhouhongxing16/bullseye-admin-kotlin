package com.chris.bullseye.system.dto

import com.chris.bullseye.system.pojo.Role
import org.springframework.security.core.GrantedAuthority
import java.time.LocalDateTime

/**
 * @author Chris
 * @date2020 12 01 17:21
 */
class User  {

    /*constructor(
            id: String?,
            username: String?,
            password: String?,
            token: String?,
            organizationId: String?,
            staffId: String?,
            departmentId: String?,
            accountLocked: Boolean?,
            accountExpired: Boolean?,
            expireTime: LocalDateTime?,
            authorities: MutableCollection<out GrantedAuthority?>?) : super(username, password, authorities) {

        this.id = id
        this.organizationId = organizationId
        this.token = token
        this.staffId = staffId
        this.departmentId = departmentId
        this.accountLocked = accountLocked
        this.accountExpired = accountExpired
        this.expireTime = expireTime
    }*/

    var id: String? = null

    var authorities: MutableCollection<out GrantedAuthority?>? = null

    var organizationId: String? = null

    var staffId: String? = null

    var name: String? = null

    var username: String? = null

    var token: String? = null

    var departmentId: String? = null

    //账户是否锁定
    var accountLocked: Boolean? = false

    //账户是否过期
    var accountExpired: Boolean? = false

    var expireTime: LocalDateTime? = null

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
