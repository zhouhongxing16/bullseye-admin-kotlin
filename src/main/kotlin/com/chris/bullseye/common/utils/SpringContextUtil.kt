package com.chris.bullseye.common.utils

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * @author Chris
 * @date 2021-09-16 9:54
 */
@Component
class SpringContextUtil: ApplicationContextAware {

    companion object{
        /**
         * Spring应用上下文环境
         */
        private var applicationContext: ApplicationContext? = null

        fun getApplicationContext(): ApplicationContext? {
            return applicationContext
        }

        /**
         * 获取对象 这里重写了bean方法，起主要作用
         */
        @Throws(BeansException::class)
        fun getBean(beanId: String?): Any? {
            return applicationContext!!.getBean(beanId!!)
        }
    }


    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        SpringContextUtil.applicationContext = applicationContext
    }

}