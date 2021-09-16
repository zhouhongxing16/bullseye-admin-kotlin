package com.chris.bullseye.system.security

import java.util.*

/**
 * @author Chris
 * @date 2021-01-04 9:25
 */
class WebFilter {

    companion object {

        private var instance: WebFilter? = null

        fun getInstance(): WebFilter? {
            if (null == instance) {
                instance = WebFilter()
                instance!!.init()
            }
            return instance
        }
    }


    var prop: Properties? = null


    private fun WeChatFilter() {}


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
//        println(url)
        var flag = false
        if (prop != null) {
            if (prop!!.containsKey(url)) {
                flag = true
            } else {
                for (item in prop!!) {
                    if (item.key.toString().endsWith("/**")) {
                        if (url.startsWith(item.key.toString().subSequence(0,item.key.toString().length-3))) {
                            flag = true
                        }
                    }
                }
            }

        } else {
            false
        }
        return flag
    }
}