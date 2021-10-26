package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@TableName(value = "b_biz_file")
open class BizFile(

        
        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "组织Id")
        @TableField(value = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "部门Id")
        @TableField(value = "department_id")
        var departmentId: String? = null,

        /**
         * 文件大小
         */
        @ApiModelProperty(name = "文件大小")
        @TableField(value = "size")
        var size: Long? = null,

        /**
         * 文件后缀（Suffix）
         */
        @ApiModelProperty(name = "文件后缀（Suffix）")
        @TableField(value = "suffix")
        var suffix: String? = null,

        /**
         * 图片略缩图相对地址
         */
        @ApiModelProperty(name = "图片略缩图相对地址")
        @TableField(value = "thumbnail_path")
        var thumbnailPath: String? = null,

        /**
         * 图片文件的宽
         */
        @ApiModelProperty(name = "图片文件的宽")
        @TableField(value = "width")
        var width: Int? = null,

        /**
         * 图片文件的高
         */
        @ApiModelProperty(name = "图片文件的高")
        @TableField(value = "height")
        var height: Int? = null,

        /**
         * 文件hash
         */
        @ApiModelProperty(name = "文件hash")
        @TableField(value = "file_hash")
        var fileHash: String? = null,

        /**
         * 相对路径
         */
        @ApiModelProperty(name = "相对路径")
        @TableField(value = "file_path")
        var relativePath: String? = null,

        /**
         * 域名
         */
        @ApiModelProperty(name = "域名")
        @TableField(value = "域名")
        var domain: String? = null,

        /**
         * 原始文件名
         */
        @ApiModelProperty(name = "原始文件名")
        @TableField(value = "original_file_name")
        var originalFileName: String? = null,

        /**
         * bucketName
         */
        @ApiModelProperty(name = "bucketName")
        @TableField(value = "bucket_name")
        var bucketName: String? = null,

        /**
         * 类型（AliOSS|qiniu|tencent|local）
         */
        @ApiModelProperty(name = "类型")
        @TableField(value = "storage_type")
        var storageType: String? = null,

        /**
         * 文件上传开始的时间
         */
        @ApiModelProperty(name = "文件上传开始的时间")
        @TableField(value = "upload_start_time")
        var uploadStartTime: LocalDateTime? = null,

        /**
         * 文件上传结束的时间
         */
        @ApiModelProperty(name = "文件上传结束的时间")
        @TableField(value = "upload_end_time")
        var uploadEndTime: LocalDateTime? = null,
        /**
         * 修改时间
         */
        @ApiModelProperty(name = "修改时间")
        @TableField(value = "update_time")
        var updateTime: LocalDateTime? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,


        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,
)
