package com.chris.bullseye.system.security

import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.Logger
import com.chris.bullseye.common.utils.SpringContextUtil
import com.chris.bullseye.system.dto.JsonResult
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Chris
 * @date2020-12-04 9:38
 */
class JWTAuthenticationFilter(manager: AuthenticationManager) : BasicAuthenticationFilter(manager) {


    @Resource
    lateinit var authUtil: AuthUtil

    @Resource
    lateinit var webFilter: WebFilter




    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val url = request.servletPath
        if( WebFilter.getInstance()!!.getUrlPassFlag(url)){
            chain.doFilter(request, response)
        }else{
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
        val token = request.getHeader("token")
        val url = request.servletPath
        return if (token == null) {
            return getFailAuthenticationTokenResult(request, response, true)
        } else {
            authUtil = SpringContextUtil.getBean("authUtil") as AuthUtil
            val user = authUtil.getCurrentLoginUser()
            if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, user.authorities)
            } else {
//                UsernamePasswordAuthenticationToken(null, null, null)
                getFailAuthenticationTokenResult(request, response, false)
            }
        }
    }

    @Throws(IOException::class)
    private fun getFailAuthenticationTokenResult(request: HttpServletRequest, response: HttpServletResponse, authFlag: Boolean): UsernamePasswordAuthenticationToken? {
        var msg = ""
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json; charset=utf-8"
        response.status = HttpStatus.UNAUTHORIZED.value()
        val writer = response.writer
        msg = if (authFlag) {
//            response.status = HttpStatus.UNAUTHORIZED.value()
            JsonResult.failed("授权认证失败，请重新登录！", HttpStatus.UNAUTHORIZED.value()).toString()
        } else {
//            response.status = HttpStatus.FORBIDDEN.value()
            JsonResult.failed("授权认证失败，请重新登录！", HttpStatus.UNAUTHORIZED.value()).toString()
        }
        writer.write(msg)
        writer.flush()
        writer.close()
        return null
    }

}
