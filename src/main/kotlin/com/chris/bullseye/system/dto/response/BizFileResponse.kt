package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class BizFileResponse(

        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "组织Id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "部门Id")
        var departmentId: String? = null,

        /**
         * 文件大小
         */
        @ApiModelProperty(name = "文件大小")
        var size: Long? = null,

        /**
         * 文件后缀（Suffix）
         */
        @ApiModelProperty(name = "文件后缀（Suffix）")
        var suffix: String? = null,

        /**
         * 图片略缩图相对地址
         */
        @ApiModelProperty(name = "图片略缩图相对地址")
        var thumbnailPath: String? = null,

        /**
         * 图片文件的宽
         */
        @ApiModelProperty(name = "图片文件的宽")
        var width: Int? = null,

        /**
         * 图片文件的高
         */
        @ApiModelProperty(name = "图片文件的高")
        var height: Int? = null,

        /**
         * 文件hash
         */
        @ApiModelProperty(name = "文件hash")
        var fileHash: String? = null,

        /**
         * 相对路径
         */
        @ApiModelProperty(name = "相对路径")
        var relativePath: String? = null,

        /**
         * 域名
         */
        @ApiModelProperty(name = "域名")
        var domain: String? = null,

        /**
         * 原始文件名
         */
        @ApiModelProperty(name = "原始文件名")
        var originalFileName: String? = null,

        /**
         * bucketName
         */
        @ApiModelProperty(name = "bucketName")
        var bucketName: String? = null,

        /**
         * 类型（AliOSS|qiniu|tencent|local）
         */
        @ApiModelProperty(name = "类型")
        var storageType: String? = null,

        /**
         * 文件上传开始的时间
         */
        @ApiModelProperty(name = "文件上传开始的时间")
        var uploadStartTime: LocalDateTime? = null,

        /**
         * 文件上传结束的时间
         */
        @ApiModelProperty(name = "文件上传结束的时间")
        var uploadEndTime: LocalDateTime? = null,
        /**
         * 修改时间
         */
        @ApiModelProperty(name = "修改时间")
        var updateTime: LocalDateTime? = null,

        @ApiModelProperty(name = "创建人ID")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        var createTime: LocalDateTime? = null,


        @ApiModelProperty(name = "状态")
        var status: Int? = null,
)
