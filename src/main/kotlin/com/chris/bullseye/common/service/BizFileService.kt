package com.chris.bullseye.common.service

import com.aliyun.oss.OSSClientBuilder
import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.google.common.hash.Hashing
import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.common.config.AliConfig
import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.common.mapper.BizFileMapper
import com.chris.bullseye.system.pojo.BizFile
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.FileUtil
import com.chris.bullseye.system.service.BaseService
import net.coobird.thumbnailator.Thumbnails
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import javax.imageio.ImageIO


/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Service
class BizFileService(var bizFileMapper: BizFileMapper, var aliOSSConfig: AliConfig) : BaseService<BizFile>() {

    override fun getMapper(): MPBaseMapper<BizFile> {
        return bizFileMapper
    }


    @Value("\${uploadConfig.prefix}")
    private val prefix: String? = null

    @Value("\${uploadConfig.uploadPath}")
    private val uploadPath: String? = null

    @Value("\${uploadConfig.domain}")
    private val domain: String? = null

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
                file.uploadStartTime = LocalDateTime.now()
                val filename = multipartFile.originalFilename
                val objectResult = ossClient.putObject(aliOSSConfig.bucketName, filename, multipartFile.inputStream)
                file.uploadEndTime = LocalDateTime.now()
                file.storageType = "AliOSS"
                file.relativePath = aliOSSConfig.bucketName + "/" + filename
                file.bucketName = aliOSSConfig.bucketName

                file.domain = domain
                file.creatorId = user!!.id
                file.creatorName = user!!.name
                file.status = 1
                val count: Int = bizFileMapper.insert(file)
            }
            result.success = true
            result.status = HttpStatus.OK.value()
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
        var filename = System.currentTimeMillis().toString() + FileUtil.getSuffix(multipartFile.originalFilename)
        val organizationPath = user!!.organizationId
        val path = "$uploadPath/$organizationPath"
        val reMap: MutableMap<String, String?> = HashMap(2)
        //计算文件hash
        val hash = Hashing.hmacSha1(multipartFile.bytes)
        var bizFile = bizFileMapper.getByFileHash(hash.toString())
        if (bizFile == null) {
            bizFile = BizFile()
            bizFile.organizationId = user!!.organizationId
            bizFile.departmentId = user!!.departmentId
            bizFile.uploadStartTime = LocalDateTime.now()
            //上传文件
            FileUtil.uploadFile(multipartFile, path, filename)
            //如果是图片则计算长宽并且压缩
            if (FileUtil.getSuffix(filename)?.let { FileUtil.isPicture(it) } == true) {
                val p = "$uploadPath/$organizationPath/thumbnail"
                val targetFile = File(p)
                if (!targetFile.exists()) {
                    targetFile.mkdirs()
                }
                val bi: Image = ImageIO.read(File("$path/$filename"))
                bizFile.width = bi.getWidth(null)
                bizFile.height = bi.getHeight(null)
                val thumbnailPath = "$prefix/$organizationPath/thumbnail/$filename"
                bizFile.thumbnailPath = thumbnailPath
                //图片压缩
                Thumbnails.of("$path/$filename").size(500, 500).toFile("$path/thumbnail/$filename")
            }
            bizFile.size = multipartFile.size
            bizFile.fileHash = hash.toString()
            bizFile.originalFileName = multipartFile.originalFilename
            bizFile.suffix = FileUtil.getSuffix(multipartFile.originalFilename)

            bizFile.uploadEndTime = LocalDateTime.now()
            bizFile.storageType = storageType
            bizFile.relativePath = path
            bizFile.domain = domain
            bizFile.creatorId = user!!.id
            bizFile.creatorName = user!!.name
            bizFile.status = 1
            val count: Int = bizFileMapper.insert(bizFile)
            result.success = count > 0
        }

        reMap["domain"] = domain
        reMap["fileId"] = bizFile.id
        reMap["relativePath"] = bizFile.relativePath
        result.data = reMap
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
            file.uploadStartTime = LocalDateTime.now()
            val filename = multipartFile.originalFilename
            val objectResult = ossClient.putObject(aliOSSConfig.bucketName, filename, multipartFile.inputStream)
            file.uploadEndTime = LocalDateTime.now()
            file.storageType = storageType
            file.relativePath = aliOSSConfig.bucketName + "/" + filename
            file.domain = aliOSSConfig.domainName
            file.bucketName = aliOSSConfig.bucketName
            file.creatorId = user!!.id
            file.creatorName = user!!.name
            file.status = 1
            val count: Int = bizFileMapper.insert(file)
            result.success = count > 0
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
                val organizationPath = user!!.organizationId
                val path = "$uploadPath/$organizationPath"
                FileUtil.deleteFile("$path/$fileName")
            } else if ("aliOSS" == storageType) {
                val ossClient = OSSClientBuilder().build(aliOSSConfig.endpoint, aliOSSConfig.accessKeyId, aliOSSConfig.accessKeySecret)
                if (!ossClient.doesBucketExist(aliOSSConfig.bucketName)) {
                    result.message = "[阿里云OSS] 无法删除文件！Bucket不存在：" + aliOSSConfig.bucketName
                } else if (!ossClient.doesObjectExist(aliOSSConfig.bucketName, fileName)) {
                    result.message = "[阿里云OSS] 文件删除失败！文件不存在：" + aliOSSConfig.bucketName.toString() + "/" + fileName
                } else {
                    ossClient.deleteObject(aliOSSConfig.bucketName, fileName)

                }
            } else if ("qiniu" == storageType) {

            }
            val count: Int = bizFileMapper.delete(Wrappers.lambdaQuery<BizFile?>().eq(BizFile::storageType, storageType).eq(BizFile::originalFileName, fileName))
            result.success = count > 0
            result.message = "删除成功"
            result.success = true
        }
        return result
    }
}