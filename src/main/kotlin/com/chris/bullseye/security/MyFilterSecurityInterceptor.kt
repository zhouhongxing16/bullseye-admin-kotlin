package com.chris.bullseye.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.SecurityMetadataSource
import org.springframework.security.access.intercept.AbstractSecurityInterceptor
import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.stereotype.Service
import javax.servlet.*

/**
 * @author Chris
 * @date 2021-01-06 9:18
 */
@Service
class MyFilterSecurityInterceptor(val securityMetadataSource: FilterInvocationSecurityMetadataSource) : AbstractSecurityInterceptor(), Filter {

    @Autowired
    fun setMyAccessDecisionManager(myAccessDecisionManager: MyAccessDecisionManager?) {
        super.setAccessDecisionManager(myAccessDecisionManager)
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val fi = FilterInvocation(request, response, chain)
        invoke(fi)
    }

    override fun destroy() {}

    operator fun invoke(fi: FilterInvocation) {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        val token: InterceptorStatusToken? = super.beforeInvocation(fi)
        try {
            //执行下一个拦截器
            fi.chain.doFilter(fi.request, fi.response)
        } finally {
            super.afterInvocation(token, null)
        }
    }

    override fun getSecureObjectClass(): Class<*>? {
        return FilterInvocation::class.java
    }

    override fun obtainSecurityMetadataSource(): SecurityMetadataSource? {
        return securityMetadataSource
    }
}