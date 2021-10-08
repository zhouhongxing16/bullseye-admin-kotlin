package com.chris.bullseye.common.utils

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.concurrent.TimeUnit

/**
 * @author Chris
 * @date 2021-10-08 10:13
 */
@Component
class DistributedLock {

    private val LOCAL_MAP: MutableMap<String, String> = HashMap()

    private var redis: StringRedisTemplate? = null

    private val LOCK_VALUE = "locked"

    fun DistributedLock(redisTemplate: StringRedisTemplate?) {
        redis = redisTemplate
    }

    private fun lock(key: String?, expireMinutes: Int): Boolean {
        val setnx = redis!!.boundValueOps(LOCK_VALUE + key).setIfAbsent(LOCK_VALUE)
        if (setnx!!) {
            redis!!.expire(LOCK_VALUE + key, expireMinutes.toLong(), TimeUnit.MINUTES)
        }
        return setnx
    }

    private fun mustLock(key: String?, expireMinutes: Int) {
        while (true) {
            val setnx = redis!!.boundValueOps(LOCK_VALUE + key).setIfAbsent(LOCK_VALUE)
            if (setnx!!) {
                redis!!.expire(LOCK_VALUE + key, expireMinutes.toLong(), TimeUnit.MINUTES)
                break
            }
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                Logger.error(e.message)
            }
        }
    }

    private fun unlock(key: String?) {
        redis!!.delete(LOCK_VALUE + key)
    }

    /***
     * 分布式锁只执行一次任务
     * @param key
     * @param expireMinutes
     * @param callBack
     * @param <T>
     * @return
    </T> */
    fun <T> doActionWithCancel(key: String, expireMinutes: Int, callBack: CallBack<T>): T? {
        val lockKey = getLockKey(key)
        synchronized(lockKey!!) {
            return try {
                if (!lock(lockKey, expireMinutes)) {
                    return null
                }
                Logger.debug("分布式任务:${lockKey} running..")
                val t = callBack.doAction()
                Logger.debug("分布式任务:${lockKey} finish..")
                //每次执行完后,休眠10s,保证多台服务器不重复执行(主要排除时差问题)
                try {
                    Thread.sleep(10000L)
                } catch (e: Exception) {
                    Logger.error("分布式任务,执行完成后,休眠10s异常!!!!!")
                }
                t
            } finally {
                //unlock(lockKey);
            }
        }
    }

    private fun getLockKey(key: String): String? {
        var lockKey = LOCAL_MAP[key]
        if (StringUtils.hasLength(lockKey)) {
            synchronized(LOCAL_MAP) {
                lockKey = LOCAL_MAP[key]
                if (StringUtils.hasLength(lockKey)) {
                    LOCAL_MAP[key] = key
                    lockKey = key
                }
            }
        }
        return lockKey
    }

    fun <T> doAction(key: String, expireMinutes: Int, callBack: CallBack<T>): T {
        val lockKey = getLockKey(key)
        synchronized(lockKey!!) {
            return try {
                mustLock(lockKey, expireMinutes)
                Logger.debug("分布式任务:${lockKey} running.." )
                val t = callBack.doAction()
                Logger.debug("分布式任务:${lockKey} finish..")
                t
            } finally {
                unlock(lockKey)
            }
        }
    }

    /***
     * 回调方法
     * @param <T>
    </T> */
    fun interface CallBack<T> {
        fun doAction(): T
    }
}