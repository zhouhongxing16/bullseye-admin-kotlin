package com.chris.bullseye.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.AntPathMatcher
import java.lang.management.ManagementFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Chris
 * @date2020 12 07 15:55
 */
class DateUtils {

    companion object{
        var YYYY = "yyyy"

        var YYYY_MM = "yyyy-MM"

        var YYYY_MM_dd = "yyyy-MM-dd"

        var YYYYMM = "yyyyMM"

        var MM = "MM"

        var YYYY_MM_DD = "yyyy-MM-dd"

        var YYYYMMDDHHMMSS = "yyyyMMddHHmmss"

        var YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

        private val parsePatterns = arrayOf(
                "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
                "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
                "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM")


        private val LOGGER: Logger = LoggerFactory.getLogger(DateUtils::class.java)
        val antPathMatcher = AntPathMatcher()

        fun getDayOfMouth(): Int {
            val a = Calendar.getInstance(Locale.CHINA)
            return a.getActualMaximum(5)
        }

        fun getCurrentTime(format: String?): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(format)
            return formatter.format(currentTime)
        }

        fun getNowDate(): Date? {
            return Date()
        }

        fun getCurrentYearMonth(): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(YYYYMM)
            return formatter.format(currentTime)
        }

        fun currentTimeMillis(): Long {
            return System.currentTimeMillis()
        }

        fun getCurrentMonth(): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(MM)
            return formatter.format(currentTime)
        }

        fun getCurrentDay(): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(YYYY_MM_dd)
            return formatter.format(currentTime)
        }

        fun getCurrentYear(): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(YYYY)
            return formatter.format(currentTime)
        }

        fun stringToDate(date: String?): Date? {
            try {
                val standarDateFormat = SimpleDateFormat(YYYY_MM_DD)
                return standarDateFormat.parse(date)
            } catch (e: Exception) {
                e.printStackTrace()
                LOGGER.error(e.message)
            }
            return null
        }

        fun toStandarDate(date: String?, format: String?): Date? {
            try {
                val standarDateFormat = SimpleDateFormat(format)
                return standarDateFormat.parse(date)
            } catch (e: Exception) {
                e.printStackTrace()
                LOGGER.error(e.message)
            }
            return null
        }

        fun getCurrentTime(): String? {
            val currentTime = Date()
            val formatter = SimpleDateFormat(YYYY_MM_DD_HH_MM_SS)
            return formatter.format(currentTime)
        }

        fun getCurrentTimeMillis(): String? {
            return System.currentTimeMillis().toString()
        }


        /**
         * 获取服务器启动时间
         */
        fun getServerStartDate(): Date? {
            val time = ManagementFactory.getRuntimeMXBean().startTime
            return Date(time)
        }


        fun parseDateToStr(format: String?, date: Date?): String {
            return SimpleDateFormat(format).format(date)
        }

        /**
         * 计算两个时间差
         */
        fun getDatePoor(endDate: Date, nowDate: Date): String? {
            val nd = (1000 * 24 * 60 * 60).toLong()
            val nh = (1000 * 60 * 60).toLong()
            val nm = (1000 * 60).toLong()
            // long ns = 1000;
            // 获得两个时间的毫秒时间差异
            val diff = endDate.time - nowDate.time
            // 计算差多少天
            val day = diff / nd
            // 计算差多少小时
            val hour = diff % nd / nh
            // 计算差多少分钟
            val min = diff % nd % nh / nm
            // 计算差多少秒//输出结果
            // long sec = diff % nd % nh % nm / ns;
            return day.toString() + "天" + hour + "小时" + min + "分钟"
        }

        @Throws(ParseException::class)
        fun getMinutes(startDate: String?, endDate: String?): String? {
            val simpleFormat = SimpleDateFormat(YYYY_MM_DD_HH_MM_SS)
            val from = simpleFormat.parse(startDate).time
            val to = simpleFormat.parse(endDate).time
            return ((to - from) / 60000L).toString()
        }
    }

}