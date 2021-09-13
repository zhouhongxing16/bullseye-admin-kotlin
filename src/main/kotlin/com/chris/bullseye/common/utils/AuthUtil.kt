package com.chris.bullseye.common.utils

import com.chris.bullseye.system.entity.Constants
import com.chris.bullseye.system.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.http.HttpServletRequest

/**
 * @author Chris
 * @date2020 12 01 17:33
 */
class AuthUtil {

    @Autowired
    private val request: HttpServletRequest? = null

    companion object{
        fun getCurrentUser(): User? {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication != null) {
                val `object` = authentication.principal
                var user: User? = null
                if (`object` is User) {
                    user = `object` as User
                    return user
                }
            }
            return null
        }

        fun getCurrentUser(request: HttpServletRequest?): User? {
            if (request == null) {
                return null
            }
            val session = request.session
            var user: User? = session.getAttribute("user") as User
            if (user == null) {
                user = getCurrentUser()
                session.setAttribute("user", user)
            }
            return user
        }

        fun changeRole(id: String?, name: String?) {
            val user: User? = getCurrentUser()
            for (role in getCurrentUser()?.roles!!) {
                if (role.id.equals(id)) {
                    if (user != null) {
                        user.currentRole = role
                    }
                }
            }
        }

        /**
         * 获取用户的数据浏览权限
         *
         * @return
         */
        fun getAuthFlag(): String? {
            val role  = getCurrentUser()?.currentRole
            val strAuths: String? = role?.dataAuthFlag
            if (strAuths != null) {
                when {
                    strAuths.contains(Constants.ORGANIZATION) -> {
                        return Constants.ORGANIZATION //全院
                    }
                    strAuths.contains(Constants.DEPARTMENT) -> {
                        return   Constants.DEPARTMENT //当前科室
                    }
                    else ->
                        return Constants.PERSONAL
                }
            }else{
                return Constants.PERSONAL
            }
        }

        fun getAuthParameterMap(map: MutableMap<String, String?>): MutableMap<String, String?> {
            val user = getCurrentUser()
            val currentRole = user?.currentRole
            val authFlag = currentRole?.dataAuthFlag
            map[Constants.ORGANIZATION_ID] = user?.organizationId
            if (currentRole != null) {
                if (Constants.ORGANIZATION == authFlag) {
                    map[Constants.ORGANIZATION_ID] = user.organizationId
                } else if (Constants.DEPARTMENT == authFlag) {
                    map[Constants.DEPARTMENT_ID] = user.departmentId
                } else if (Constants.PERSONAL == authFlag) {
                    map[Constants.DEPARTMENT_ID] = user.departmentId
                    map[Constants.STAFF_ID] = user.staffId
                }
            }
            return map
        }
    }

}