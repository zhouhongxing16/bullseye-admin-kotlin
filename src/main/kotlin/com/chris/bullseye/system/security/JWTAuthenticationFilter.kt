package com.chris.bullseye.system.security

import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.Logger
import com.chris.bullseye.system.entity.JsonResult
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
class JWTAuthenticationFilter(manager: AuthenticationManager): BasicAuthenticationFilter(manager) {


    @Resource lateinit var authUtil: AuthUtil


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
        val token = request.getHeader("token")
        val url = request.servletPath
       return if (token == null) {
            return getFailAuthenticationTokenResult(request, response, true)
        } else {
            val user = authUtil.getCurrentLoginUser()
            if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, user.authorities)
            } else {
                UsernamePasswordAuthenticationToken(null, null, null)
            }
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
