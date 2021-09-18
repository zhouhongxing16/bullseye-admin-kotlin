package com.chris.bullseye.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.AntPathMatcher
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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



        fun getCurrentTime(): String? {
            var now = LocalDateTime.now()
            val dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS)
            return dtf.format(now)
        }

        fun getCurrentTimeMillis(): String? {
            return System.currentTimeMillis().toString()
        }



        /***
         * 获取2个时间间隔的分钟数
         * @param begin
         * @param end
         * @return
         */
        fun getDurationMinute(begin: LocalDateTime?, end: LocalDateTime?): Long {
            val duration = Duration.between(begin, end)
            return duration.toMinutes()
        }

    }

}