package com.chris.bullseye.system.security

import java.util.*

/**
 * @author Chris
 * @date 2021-01-04 9:25
 */
class WebFilter {

    private var instance: WebFilter? = null

    var prop: Properties? = null


    private fun WeChatFilter() {}


    fun getInstance(): WebFilter? {
        if (null == instance) {
            instance = WebFilter()
            instance!!.init()
        }
        return instance
    }

    @Synchronized
    private fun init() {
        val `is` = this.javaClass.classLoader.getResourceAsStream("properties/web-filter.properties")
        prop = Properties()
        try {
            prop!!.load(`is`)
            `is`.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUrlPassFlag(url: String): Boolean {
        println(url)
        return if (prop != null) {
            prop!!.containsKey(url)
        } else {
            false
        }
    }
}