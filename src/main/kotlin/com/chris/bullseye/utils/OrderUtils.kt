package com.chris.bullseye.utils

import org.apache.commons.lang3.StringUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Chris
 * @date 2021-01-21 14:36
 */
class OrderUtils {
    companion object{
        private val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        private val atomicInteger = AtomicInteger(1000000)

        /**
         * 创建不连续的订单号
         *
         * @param no
         * 数据中心编号
         * @return 唯一的、不连续订单号
         */
        @Synchronized
        fun getOrderNoByUUID(no: String): String? {
            var uuidHashCode = UUID.randomUUID().toString().hashCode()
            if (uuidHashCode < 0) {
                uuidHashCode = uuidHashCode * -1
            }
            val date = simpleDateFormat.format(Date())
            return no + date + uuidHashCode
        }

        /**
         * 获取同一秒钟 生成的订单号连续
         *
         * @param
         * 数据中心编号
         * @return 同一秒内订单连续的编号
         */
        @Synchronized
        fun getOrderNoByAtomic(start: String): String? {
            var start = start
            if (StringUtils.isEmpty(start)) {
                start = "HDD"
            }
            atomicInteger.getAndIncrement()
            val i = atomicInteger.get()
            val date = simpleDateFormat.format(Date())
            return start + date + i
        }
    }
}