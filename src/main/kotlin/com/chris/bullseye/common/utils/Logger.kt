package com.chris.bullseye.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Chris
 * @date2020 12 04 14:11
 */
class Logger {


    companion object{
        private val logger: Logger = LoggerFactory.getLogger(Logger::class.java)
        fun info(msg: String?) {
            this.logger.info(msg)
        }

        fun error(msg: String?) {
            logger.error(msg)
        }

        fun debug(msg: String?) {
            logger.debug(msg)
        }

        /**
         * 添加space
         * @param sb
         * @param indent
         */
        private fun addIndentBlank(sb: StringBuilder, indent: Int) {
            for (i in 0 until indent) {
                sb.append('\t')
            }
        }

        /**
         * 格式化
         * @param jsonStr
         * @return
         */
        fun formatJson(jsonStr: String?): String? {
            if (null == jsonStr || "" == jsonStr) {
                return ""
            }
            val sb = StringBuilder()
            sb.append("\n")
            var last = '\u0000'
            var current = '\u0000'
            var indent = 0
            for (i in 0 until jsonStr.length) {
                last = current
                current = jsonStr[i]
                when (current) {
                    '{', '[' -> {
                        sb.append(current)
                        sb.append('\n')
                        indent++
                        addIndentBlank(sb, indent)
                    }
                    '}', ']' -> {
                        sb.append('\n')
                        indent--
                        addIndentBlank(sb, indent)
                        sb.append(current)
                    }
                    ',' -> {
                        sb.append(current)
                        if (last != '\\') {
                            sb.append('\n')
                            addIndentBlank(sb, indent)
                        }
                    }
                    else -> sb.append(current)
                }
            }
            return sb.toString()
        }
    }


}