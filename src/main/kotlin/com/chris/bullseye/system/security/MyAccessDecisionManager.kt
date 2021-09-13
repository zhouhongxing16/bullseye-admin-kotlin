package com.chris.bullseye.system.security

import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class MyAccessDecisionManager : AccessDecisionManager {
    /**
     * 决策方法： 如果方法执行完毕没有抛出异常,则说明可以放行, 否则抛出异常 AccessDeniedException
     */
    @Throws(AccessDeniedException::class, InsufficientAuthenticationException::class)
    override fun decide(authentication: Authentication, `object`: Any, configAttributes: Collection<ConfigAttribute>) {
        // System.out.println("---------------decide------------------");
        // 如果登陆成功,则信息会存储在Authorities中
        val myRoles = authentication.authorities
        // 如果前面的 getAttributes() 返回非空,则返回的数据做为形参传入, 如果返回为null 则不会进入decide() 直接放行

        // System.out.println("myRole:" + myRoles);
        // System.out.println("sysRole:" + configAttributes);
        for (myRole in myRoles) { // 当前登录的角色
            for (urlRoles in configAttributes) { // 前台传入的url的角色，UrlDaoImpl.getAttributes获得的
                if (myRole.authority == urlRoles.attribute) {
                    // 说明此URL地址符合权限,可以放行
                    return
                }
            }
        }
        throw AccessDeniedException("权限越界！")
    }

    override fun supports(attribute: ConfigAttribute): Boolean {
        return true
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return true
    }
}
