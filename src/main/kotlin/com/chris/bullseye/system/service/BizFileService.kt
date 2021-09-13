package com.chris.bullseye.system.service

import com.aliyun.oss.OSSClientBuilder
import com.google.common.hash.Hashing
import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.common.config.AliConfig
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.mapper.BizFileMapper
import com.chris.bullseye.system.pojo.BizFile
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.FileUtil
import net.coobird.thumbnailator.Thumbnails
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO


/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Service
class BizFileService(var bizFileMapper: BizFileMapper, var aliOSSConfig: AliConfig) : BaseService<BizFile>() {

    override fun getMapper(): BaseMapper<BizFile> {
        return bizFileMapper
    }


    @Value("\${uploadConfig.prefix}")
    private val prefix: String? = null

    @Value("\${uploadConfig.uploadPath}")
    private val uploadPath: String? = null

    @Value("\${uploadConfig.storageType}")
    private val storageType: String? = null


    fun upload(files: Array<MultipartFile>?): JsonResult<BizFile> {
        val result = JsonResult<BizFile>()
        val user = AuthUtil.getCurrentUser()
        if (null == files || files.isEmpty()) {
            result.message = "请至少上传一个文件！"
        } else {
            // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
            val ossClient = OSSClientBuilder().build(aliOSSConfig.endpoint, aliOSSConfig.accessKeyId, aliOSSConfig.accessKeySecret)
            if (!ossClient.doesBucketExist(aliOSSConfig.bucketName)) {
                result.message = "[阿里云OSS] 无法上传文件！Bucket不存在：" + aliOSSConfig.bucketName
            }
            var file: BizFile? = null
            for (multipartFile in files) {
                file = BizFile()
                val hash = Hashing.sha1().hashBytes(multipartFile.bytes)
                file.fileHash = hash.toString()
                file.size = multipartFile.size
                file.originalFileName = multipartFile.originalFilename
                file.suffix = FileUtil.getSuffix(multipartFile.originalFilename)
                file.uploadStartTime = Date()
                val filename = multipartFile.originalFilename
                val objectResult = ossClient.putObject(aliOSSConfig.bucketName, filename, multipartFile.inputStream)
                file.uploadEndTime = Date()
                file.storageType = "AliOSS"
                file.filePath = aliOSSConfig.bucketName + "/" + filename
                file.fullFilePath = aliOSSConfig.bucketName + filename
                file.bucketName = aliOSSConfig.bucketName
                file.userId = user!!.id
                file.status = 1
                val count: Int = bizFileMapper.insert(file)
            }
            result.success = true
            result.status =  HttpStatus.OK.value()
        }

        // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        return result
    }

    fun uploadSingleFile(multipartFile: MultipartFile?): JsonResult<BizFile> {
        var result = JsonResult<BizFile>()
        if (null == multipartFile) {
            result.message = "请上传一个文件！"
        } else {
            if ("local" == storageType) {
                result = localUpload(multipartFile)
            } else if ("aliOSS" == storageType) {
                result = aliOSSUpload(multipartFile)
            } else if ("qiniu" == storageType) {
            }
        }
        return result
    }

    private fun localUpload(multipartFile: MultipartFile): JsonResult<BizFile> {
        val result = JsonResult<BizFile>()
        val user = AuthUtil.getCurrentUser()
        var filename =  System.currentTimeMillis().toString() + FileUtil.getSuffix(multipartFile.originalFilename)
        val organizationPath = user!!.organizationId
        val path = "$uploadPath/$organizationPath"
        val viewPath = "$prefix/$organizationPath/$filename"

        //计算文件hash
        val hash = Hashing.sha1().hashBytes(multipartFile.bytes)
        val file = BizFile()
        file.organizationId = user!!.organizationId
        file.departmentId = user!!.departmentId
        file.uploadStartTime = Date()
        //上传文件
        FileUtil.uploadFile(multipartFile, path, filename)
        if (FileUtil.getSuffix(filename)?.let { FileUtil.isPicture(it) } == true) {
            val p = "$uploadPath/$organizationPath/thumbnail"
            val targetFile = File(p)
            if (!targetFile.exists()) {
                targetFile.mkdirs()
            }
            val bi: Image = ImageIO.read(File("$path/$filename"))
            file.width = bi.getWidth(null)
            file.height = bi.getHeight(null)
            val thumbnailPath = "$prefix/$organizationPath/thumbnail/$filename"
            file.thumbnail = thumbnailPath
            //图片压缩
            Thumbnails.of("$path/$filename").size(500, 500).toFile("$path/thumbnail/$filename")
        }
        file.size = multipartFile.size
        file.fileHash = hash.toString()
        file.originalFileName = multipartFile.originalFilename
        file.suffix = FileUtil.getSuffix(multipartFile.originalFilename)

        file.uploadEndTime = Date()
        file.storageType = storageType
        file.filePath = path
        file.fullFilePath = viewPath
        file.userId = user!!.id
        file.status = 1
        val count: Int = bizFileMapper.insert(file)
        result.success = if (count > 0) true else false
        result.data = file
        result.status = HttpStatus.OK.value()
        return result
    }

    @Throws(IOException::class)
    private fun aliOSSUpload(multipartFile: MultipartFile?): JsonResult<BizFile> {
        val result = JsonResult<BizFile>()
        val user = AuthUtil.getCurrentUser()
        if (null == multipartFile) {
            result.message = "请上传一个文件！"
        } else {
            // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
            val ossClient = OSSClientBuilder().build(aliOSSConfig.endpoint, aliOSSConfig.accessKeyId, aliOSSConfig.accessKeySecret)
            if (!ossClient.doesBucketExist(aliOSSConfig.bucketName)) {
                result.message = "[阿里云OSS] 无法上传文件！Bucket不存在：" + aliOSSConfig.bucketName
            }
            val hash = Hashing.sha1().hashBytes(multipartFile.bytes)
            val file = BizFile()
            file.size = multipartFile.size
            file.fileHash = hash.toString()
            file.originalFileName = multipartFile.originalFilename
            file.suffix = FileUtil.getSuffix(multipartFile.originalFilename)
            file.uploadStartTime = Date()
            val filename = multipartFile.originalFilename
            val objectResult = ossClient.putObject(aliOSSConfig.bucketName, filename, multipartFile.inputStream)
            file.uploadEndTime = Date()
            file.storageType = storageType
            file.filePath = aliOSSConfig.bucketName + "/" + filename
            file.fullFilePath = aliOSSConfig.domainName + filename
            file.bucketName = aliOSSConfig.bucketName
            file.userId = user!!.id
            file.status = 1
            val count: Int = bizFileMapper.insert(file)
            result.success = if (count > 0) true else false
            result.data = file
            result.status = 200
        }
        // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        return result
    }


    fun removeFile(fileName: String): JsonResult<BizFile> {
        val result = JsonResult<BizFile>()
        val user = AuthUtil.getCurrentUser()
        result.success = false
        if (fileName.isNullOrEmpty()) {
            result.message = "[$fileName]删除文件失败：文件key为空"
        } else {
            if ("local" == storageType) {
                val file = BizFile()
                file.originalFileName = fileName
                file.storageType = storageType
                val organizationPath= user!!.organizationId
                val path = "$uploadPath/$organizationPath"
                FileUtil.deleteFile("$path/$fileName")
                val count: Int = bizFileMapper.delete(file)
                result.success = if (count > 0) true else false
                result.message = "删除成功"
                result.success = true
            } else if ("aliOSS" == storageType) {
                val ossClient = OSSClientBuilder().build(aliOSSConfig.endpoint, aliOSSConfig.accessKeyId, aliOSSConfig.accessKeySecret)
                if (!ossClient.doesBucketExist(aliOSSConfig.bucketName)) {
                    result.message = "[阿里云OSS] 无法删除文件！Bucket不存在：" + aliOSSConfig.bucketName
                } else if (!ossClient.doesObjectExist(aliOSSConfig.bucketName, fileName)) {
                    result.message = "[阿里云OSS] 文件删除失败！文件不存在：" + aliOSSConfig.bucketName.toString() + "/" + fileName
                } else {
                    ossClient.deleteObject(aliOSSConfig.bucketName, fileName)
                    val file = BizFile()
                    file.originalFileName = fileName
                    file.bucketName = aliOSSConfig.bucketName
                    val count: Int = bizFileMapper.delete(file)
                    result.success = if (count > 0) true else false
                    result.message = "删除成功"
                    result.success = true
                }
            } else if ("qiniu" == storageType) {
            }
        }
        return result
    }
}