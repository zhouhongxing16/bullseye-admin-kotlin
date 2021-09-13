package com.chris.bullseye.common.utils

import org.slf4j.LoggerFactory
import java.net.InetAddress
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest

/**
 * @author Chris
 * @date2020 12 07 16:01
 */
class IPUtils {
    companion object{

        private val logger = LoggerFactory.getLogger(IPUtils::class.java)

        /**
         * 获取IP地址
         *
         *
         * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
         * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
         */
        fun getIpAddr(request: HttpServletRequest): String? {
            var ip = request.getHeader("x-forwarded-for")
            if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("Proxy-Client-IP")
            }
            if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("WL-Proxy-Client-IP")
            }
            if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.remoteAddr
            }
            return if ("0:0:0:0:0:0:0:1" == ip) "127.0.0.1" else ip
        }


        fun internalIp(ip: String): Boolean {
            val address = textToNumericFormatV4(ip)
            return internalIp(address) || "127.0.0.1" == ip
        }


        private fun internalIp(address: ByteArray?): Boolean {
            val b0 = address!![0]
            val b1 = address[1]
            // 10.x.x.x/8
            val SECTION_1: Byte = 0x0A
            // 172.16.x.x/12
            val SECTION_2 = 0xAC.toByte()
            val SECTION_3 = 0x10.toByte()
            val SECTION_4 = 0x1F.toByte()
            // 192.168.x.x/16
            val SECTION_5 = 0xC0.toByte()
            val SECTION_6 = 0xA8.toByte()
            return when (b0) {
                SECTION_1 -> true
                SECTION_2 -> {
                    if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                        return true
                    }
                    when (b1) {
                        SECTION_6 -> return true
                    }
                    false
                }
                SECTION_5 -> {
                    when (b1) {
                        SECTION_6 -> return true
                    }
                    false
                }
                else -> false
            }
        }

        /**
         * 将IPv4地址转换成字节
         *
         * @param text IPv4地址
         * @return byte 字节
         */
        fun textToNumericFormatV4(text: String): ByteArray? {
            if (text.length == 0) {
                return null
            }
            val bytes = ByteArray(4)
            val elements = text.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            try {
                var l: Long
                var i: Int
                when (elements.size) {
                    1 -> {
                        l = elements[0].toLong()
                        if (l < 0L || l > 4294967295L) {
                            return null
                        }
                        bytes[0] = (l shr 24 and 0xFF).toInt().toByte()
                        bytes[1] = (l and 0xFFFFFF shr 16 and 0xFF).toInt().toByte()
                        bytes[2] = (l and 0xFFFF shr 8 and 0xFF).toInt().toByte()
                        bytes[3] = (l and 0xFF).toInt().toByte()
                    }
                    2 -> {
                        l = elements[0].toInt().toLong()
                        if (l < 0L || l > 255L) {
                            return null
                        }
                        bytes[0] = (l and 0xFF).toInt().toByte()
                        l = elements[1].toInt().toLong()
                        if (l < 0L || l > 16777215L) {
                            return null
                        }
                        bytes[1] = (l shr 16 and 0xFF).toInt().toByte()
                        bytes[2] = (l and 0xFFFF shr 8 and 0xFF).toInt().toByte()
                        bytes[3] = (l and 0xFF).toInt().toByte()
                    }
                    3 -> {
                        i = 0
                        while (i < 2) {
                            l = elements[i].toInt().toLong()
                            if (l < 0L || l > 255L) {
                                return null
                            }
                            bytes[i] = (l and 0xFF).toInt().toByte()
                            ++i
                        }
                        l = elements[2].toInt().toLong()
                        if (l < 0L || l > 65535L) {
                            return null
                        }
                        bytes[2] = (l shr 8 and 0xFF).toInt().toByte()
                        bytes[3] = (l and 0xFF).toInt().toByte()
                    }
                    4 -> {
                        i = 0
                        while (i < 4) {
                            l = elements[i].toInt().toLong()
                            if (l < 0L || l > 255L) {
                                return null
                            }
                            bytes[i] = (l and 0xFF).toInt().toByte()
                            ++i
                        }
                    }
                    else -> return null
                }
            } catch (e: NumberFormatException) {
                return null
            }
            return bytes
        }

        fun getHostIp(): String? {
            try {
                return InetAddress.getLocalHost().hostAddress
            } catch (e: UnknownHostException) {
            }
            return "127.0.0.1"
        }

        fun getHostName(): String? {
            try {
                return InetAddress.getLocalHost().hostName
            } catch (e: UnknownHostException) {
            }
            return "未知"
        }
    }


}