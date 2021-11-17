package com.chris.bullseye.common.utils

import com.chris.bullseye.common.utils.DateUtils.Companion.getDurationMinute
import com.chris.bullseye.system.dto.Constants
import com.chris.bullseye.system.dto.User
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest

/**
 * @author Chris
 * @date2020 12 01 17:33
 */
@Component
class AuthUtil {


    @Autowired
    lateinit var redisUtil: RedisUtil

    private val LOGIN_USER_TOKEN_PREFIX = "login:user-token:"


    /***
     * 用户token续期超时时间,单位:分钟
     */
    private val LOGIN_OUT_TIME_MINUTE = 30L

    /***
     * redis 用户信息过期时间  2小时
     */
    private val REDIS_OUT_TIME = (60 * 2).toLong()

    /***
     * 通过token获取当前用户
     * @param token
     * @return
     */
    fun getUserByToken(token: String): User? {
        val key = LOGIN_USER_TOKEN_PREFIX + token
        if (!key.isNullOrEmpty()) {
            val userStr: Any? = redisUtil.get(key)

            return if (userStr == null) {
                null
            } else {
                userStr as String
                // val loginUser: User = JSON.parseObject(userStr, User::class.java)
                val loginUser: User = Gson().fromJson(userStr, User::class.java)
                        // 验证登录失效时间(距离失效时间小于15分钟时,重新设置用户redis信息)
                if (loginUser.expireTime != null &&
                        getDurationMinute(LocalDateTime.now(), loginUser.expireTime) <= LOGIN_OUT_TIME_MINUTE) {
                    // 更新Redis中保存的用户信息(token有效期增加2小时)
                    setUserInfo(token, loginUser)
                }
                loginUser
            }

        } else {
            return null
        }

    }

    fun getCurrentLoginUser(request: HttpServletRequest): User? {
        val token = request.getHeader("token")
        return if (token.isNullOrEmpty()) {
            null
        } else {
            getUserByToken(token)
        }
    }

    fun getCurrentLoginUser(): User? {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
        val token = request.getHeader("token")
        return if (token.isNullOrEmpty()) {
            null
        } else {
            getUserByToken(token)
        }
    }

    /***
     * 设置redis里面的用户信息,以及过期时间
     * @param token
     * @param loginUser
     */
    fun setUserInfo(token: String, loginUser: User) {
        if (loginUser.token.isNullOrEmpty()) {
            loginUser.token = token
        }
        loginUser.expireTime = loginUser.expireTime!!.plusHours(2)
        redisUtil.setEx(LOGIN_USER_TOKEN_PREFIX + token, Gson().toJson(loginUser), REDIS_OUT_TIME, TimeUnit.MINUTES)
    }

    /***
     * 只更新redis里面的用户信息
     * @param token
     * @param loginUser
     */
    fun setAndUpdateUserInfo(token: String, loginUser: User) {
        //登陆失效时间
        if (loginUser.token.isNullOrEmpty()) {
            loginUser.token = token
        }
        redisUtil!!.setEx(LOGIN_USER_TOKEN_PREFIX + token, Gson().toJson(loginUser), REDIS_OUT_TIME, TimeUnit.MINUTES)
    }

    /***
     * 移除用户信息
     * @param token
     */
    fun removeUserInfo(token: String) {
        redisUtil.delete(LOGIN_USER_TOKEN_PREFIX + token)
    }

    companion object {
        fun getCurrentUser(): User? {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication != null) {
                val obj = authentication.principal
                var user: User?
                if (obj is User) {
                    user = obj
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
            val role = getCurrentUser()?.currentRole
            val strAuths: String? = role?.dataAuthFlag
            return if (strAuths != null) {
                when {
                    strAuths.contains(Constants.ORGANIZATION) -> {
                        Constants.ORGANIZATION //组织
                    }
                    strAuths.contains(Constants.DEPARTMENT) -> {
                        Constants.DEPARTMENT //当前部门
                    }
                    else ->
                        Constants.PERSONAL
                }
            } else {
                Constants.PERSONAL
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
