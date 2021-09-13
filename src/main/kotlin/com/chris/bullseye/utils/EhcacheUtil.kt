package com.chris.bullseye.utils

import net.sf.ehcache.CacheManager
import net.sf.ehcache.Element

/**
 * @author Chris
 * @date 2020 12 07 17:58
 */
class EhcacheUtil {

    companion object {
        var manager = CacheManager.create()

        var cacheName = "metaCache"

        operator fun get(key: Any?): Any? {
            val cache = manager.getCache(cacheName)
            if (cache != null) {
                val element = cache[key]
                if (element != null) {
                    return element.objectValue
                }
            }
            return null
        }

        fun put(key: Any?, value: Any?) {
            val cache = manager.getCache(cacheName)
            cache?.put(Element(key, value))
        }

        fun remove(key: Any?): Boolean {
            val cache = manager.getCache(cacheName)
            return cache?.remove(key) ?: false
        }
    }


}