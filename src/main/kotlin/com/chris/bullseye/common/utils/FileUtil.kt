package com.chris.bullseye.common.utils

import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.util.*


class FileUtil {
    companion object {
        private val PICTURE_SUFFIXS = arrayOf(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg")

        fun isPicture(suffix: String): Boolean {
            return !suffix.isNullOrEmpty() && Arrays.asList(*PICTURE_SUFFIXS).contains(suffix.toLowerCase())
        }

        @Throws(Exception::class)
        fun uploadFile(multipartFile: MultipartFile, filePath: String, fileName: String) {
            //路径为空就略过
            if (filePath.isNullOrEmpty()) {
                return
            }
            val targetFile = File(filePath)
            if (!targetFile.exists()) {
                targetFile.mkdirs()
            }
            multipartFile.transferTo(File("$filePath/$fileName"))
        }

        private fun setFileReadMode(path: String) {
            //默认增加可读权限
            val accessFile = "chmod a+r $path"
            try {
                val prop = System.getProperties()
                val os = prop.getProperty("os.name")
                if (os != null && os.toLowerCase().indexOf("windows") == -1) {
                    Runtime.getRuntime().exec(accessFile)
                }
            } catch (ex: Exception) {
                Logger.error(ex.message)
            }
        }

        fun deleteFile(fileName: String?): Boolean {
            val file = File(fileName)
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            return if (file.exists() && file.isFile) {
                file.delete()
            } else {
                false
            }
        }

        fun renameToUUID(name: String): String? {
            return UUID.randomUUID().toString() + "." + name.substring(name.lastIndexOf(".") + 1)
        }

        fun getSuffixByUrl(imgUrl: String): String? {
            val defaultSuffix = ".png"
            if (imgUrl.isNullOrEmpty()) {
                return defaultSuffix
            }
            var fileName = imgUrl
            if (imgUrl.contains("/")) {
                fileName = imgUrl.substring(imgUrl.lastIndexOf("/"))
            }
            val fileSuffix = getSuffix(fileName)
            return if (fileSuffix.isNullOrEmpty()) defaultSuffix else fileSuffix
        }

        fun getSuffix(fileName: String?): String? {
            return if (fileName.isNullOrEmpty()) {
                null
            } else {
                var index = fileName.lastIndexOf(".")
                index = if (-1 == index) fileName.length else index
                fileName.substring(index)
            }
        }

        /**
         * 读取本地html文件里的html代码
         * @param file  File file=new File("文件的绝对路径")
         * @return
         */
        fun toHtmlString(filePath: String): String? {
            var file = File(filePath)
            // 获取HTML文件流
            val htmlSb = StringBuffer()
            try {
                val br = BufferedReader(InputStreamReader(
                        FileInputStream(file), "utf-8"))
                while (br.ready()) {
                    htmlSb.append(br.readLine())
                }
                br.close()
                // 删除临时文件
                //file.delete();
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            // HTML文件字符串
            // 返回经过清洁的html文本
            return htmlSb.toString()
        }
    }
}