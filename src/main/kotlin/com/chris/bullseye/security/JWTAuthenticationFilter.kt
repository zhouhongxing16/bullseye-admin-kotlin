package com.chris.bullseye.security

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.User
import com.chris.bullseye.pojo.Role
import com.chris.bullseye.utils.JwtHelper
import com.chris.bullseye.utils.Logger
import io.jsonwebtoken.Claims
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Chris
 * @date2020-12-04 9:38
 */
class JWTAuthenticationFilter(manager: AuthenticationManager): BasicAuthenticationFilter(manager) {



    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")
        val url = request.servletPath
        if (header == null || !header.startsWith("Bearer ")) {
            // 如果头部 Authorization 未设置或者不是 Bearer 认证头部，则当前
            // 请求不是该过滤器关注的对象，直接放行，继续filter chain 的执行
            chain.doFilter(request, response)
            return
        }else if (header != null && !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            response.status = HttpStatus.UNAUTHORIZED.value()
            request.characterEncoding = "UTF-8"
            response.setHeader("content-type", "text/html;charset=UTF-8")
            response.characterEncoding = "UTF-8"
            val writer = response.writer
            Logger.debug("授权认证失败，请重新登录")
            val msg = JsonResult.failed("授权认证失败，请重新登录",HttpStatus.UNAUTHORIZED.value()).toString()
            writer.write(msg)
            writer.flush()
            writer.close()
            return
        } else {
            val authentication = getAuthentication(request, response)
            if (authentication != null) {
                SecurityContextHolder.getContext().authentication = authentication
                chain.doFilter(request, response)
            } else {
                Logger.debug("授权认证失败，请重新登录")
            }
        }
    }

    private fun getAuthentication(request: HttpServletRequest, response: HttpServletResponse): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader("Authorization")
        val url = request.servletPath
        return if ("null" != token.replace("Bearer ", "").trim { it <= ' ' }) {
            var user: User? = null
            try {
                val claims = JwtHelper.verifyJwt(token)
                if (claims == null) {
                    return getFailAuthenticationTokenResult(request, response, true)
                } else {
                    val obj = JwtHelper.tokenToJSON(token)
                    if(!obj.isNullOrEmpty()){
                        val ja  =JSONObject.parseArray(obj?.getString("authorities"))
                        val roleList = JSONObject.parseArray(obj?.getString("authorities"),Role::class.java)
                        var role = JSONObject.parseObject(obj.getString("currentRole"),Role::class.java)
                        user = User(obj.getString("username"), "", ja.toJavaList(GrantedAuthority::class.java))
                        user.staffId = obj.getString("staffId")
                        user.platform = obj.getString("platform")
                        user.id = obj.getString("id")
                        user.organizationId =obj.getString("organizationId")
                        user.departmentId=obj.getString("departmentId")
                        user.roles =roleList
                        user.currentRole = role
                    }
                }
            } catch (e: Exception) {
                println("授权认证失败，请重新登录")
                println(e.message)
//                Logger.debug("授权认证失败，请重新登录")
                return getFailAuthenticationTokenResult(request, response, true)
            }
            if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
            } else {
                UsernamePasswordAuthenticationToken(null, null, null)
            }
        } else {
            getFailAuthenticationTokenResult(request, response, true)
        }
    }

    @Throws(IOException::class)
    private fun getFailAuthenticationTokenResult(request: HttpServletRequest, response: HttpServletResponse, authFlag: Boolean): UsernamePasswordAuthenticationToken? {
        var msg = ""
        response.setHeader("content-type", "text/html;charset=UTF-8")
        response.characterEncoding = "UTF-8"
        val writer = response.writer
        if (authFlag) {
//            response.status = HttpStatus.UNAUTHORIZED.value()
            msg =  JsonResult.failed("授权认证失败，请重新登录！",HttpStatus.UNAUTHORIZED.value()).toString()
        } else {
//            response.status = HttpStatus.FORBIDDEN.value()
            msg =  JsonResult.failed("授权认证失败，请重新登录！",HttpStatus.UNAUTHORIZED.value()).toString()
        }
        writer.write(msg)
        writer.flush()
        writer.close()
        return null
    }

}
