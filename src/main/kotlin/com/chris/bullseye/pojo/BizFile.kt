package com.chris.bullseye.pojo

import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Table(name = "kb_biz_file")
open class BizFile(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "组织Id")
        @Column(name = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "部门Id")
        @Column(name = "department_id")
        var departmentId: String? = null,

        /**
         * 文件大小
         */
        @ApiModelProperty(name = "文件大小")
        @Column(name = "size")
        var size: Long? = null,

        /**
         * 文件后缀（Suffix）
         */
        @ApiModelProperty(name = "文件后缀（Suffix）")
        @Column(name = "suffix")
        var suffix: String? = null,

        /**
         * 图片略缩图
         */
        @ApiModelProperty(name = "图片略缩图")
        @Column(name = "thumbnail")
        var thumbnail: String? = null,

        /**
         * 图片文件的宽
         */
        @ApiModelProperty(name = "图片文件的宽")
        @Column(name = "width")
        var width: Int? = null,

        /**
         * 图片文件的高
         */
        @ApiModelProperty(name = "图片文件的高")
        @Column(name = "height")
        var height: Int? = null,

        /**
         * 文件hash
         */
        @ApiModelProperty(name = "文件hash")
        @Column(name = "file_hash")
        var fileHash: String? = null,

        /**
         * 文件路径 （不带域名）
         */
        @ApiModelProperty(name = "文件路径 （不带域名）")
        @Column(name = "file_path")
        var filePath: String? = null,

        /**
         * 文件全路径 （带域名）
         */
        @ApiModelProperty(name = "件全路径 （带域名）")
        @Column(name = "full_file_path")
        var fullFilePath: String? = null,

        /**
         * 原始文件名
         */
        @ApiModelProperty(name = "原始文件名")
        @Column(name = "original_file_name")
        var originalFileName: String? = null,

        /**
         * 原始文件名
         */
        @ApiModelProperty(name = "bucketName")
        @Column(name = "bucket_name")
        var bucketName: String? = null,

        /**
         * 类型（AliOSS|qiniu|tencent|local）
         */
        @ApiModelProperty(name = "类型")
        @Column(name = "storage_type")
        var storageType: String? = null,

        /**
         * 文件上传开始的时间
         */
        @ApiModelProperty(name = "文件上传开始的时间")
        @Column(name = "upload_start_time")
        var uploadStartTime: Date? = null,

        /**
         * 文件上传结束的时间
         */
        @ApiModelProperty(name = "文件上传结束的时间")
        @Column(name = "upload_end_time")
        var uploadEndTime: Date? = null,

        /**
         * 创建时间
         */
        @ApiModelProperty(name = "创建时间")
        @Column(name = "created")
        var created: Date? = null,

        /**
         * 修改时间
         */
        @ApiModelProperty(name = "修改时间")
        @Column(name = "updated")
        var updated: Date? = null,

        /**
         * 创建人
         */
        @ApiModelProperty(name = "创建人")
        @Column(name = "user_id")
        var userId: String? = null,

        /**
         * 文件上传结束的时间
         */
        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        )
