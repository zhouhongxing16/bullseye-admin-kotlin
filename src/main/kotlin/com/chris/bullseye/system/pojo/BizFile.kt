package com.chris.bullseye.system.pojo

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Table(name = "b_biz_file")
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
         * 图片略缩图相对地址
         */
        @ApiModelProperty(name = "图片略缩图相对地址")
        @Column(name = "thumbnail_path")
        var thumbnailPath: String? = null,

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
         * 相对路径
         */
        @ApiModelProperty(name = "相对路径")
        @Column(name = "file_path")
        var relativePath: String? = null,

        /**
         * 域名
         */
        @ApiModelProperty(name = "域名")
        @Column(name = "域名")
        var domain: String? = null,

        /**
         * 原始文件名
         */
        @ApiModelProperty(name = "原始文件名")
        @Column(name = "original_file_name")
        var originalFileName: String? = null,

        /**
         * bucketName
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
        var uploadStartTime: LocalDateTime? = null,

        /**
         * 文件上传结束的时间
         */
        @ApiModelProperty(name = "文件上传结束的时间")
        @Column(name = "upload_end_time")
        var uploadEndTime: LocalDateTime? = null,
        /**
         * 修改时间
         */
        @ApiModelProperty(name = "修改时间")
        @Column(name = "update_time")
        var updateTime: LocalDateTime? = null,

        @ApiModelProperty(name = "创建人ID")
        @Column(name = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "create_time")
        var createTime: LocalDateTime? = null,


        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,
)
