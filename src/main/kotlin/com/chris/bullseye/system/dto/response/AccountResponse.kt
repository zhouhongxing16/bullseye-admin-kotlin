package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date2020 12 07 10:53
 */
data class AccountResponse(

        @ApiModelProperty(name = "组织名称")
        var organizationName: String? = null,

        @ApiModelProperty(name = "部门名称")
        var departmentName: String? = null,

        @ApiModelProperty(name = "姓名")
        var staffName: String? = null,

        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "姓名")
        var name: String? = null,

        @ApiModelProperty(name = "账户")
        var username: String? = null,

        @ApiModelProperty(name = "昵称")
        var nickName: String? = null,


        @ApiModelProperty(name = "邮箱")
        var email: String? = null,

        @ApiModelProperty(name = "手机号码")
        var mobile: String? = null,

        @ApiModelProperty(name = "是否开通手机号登录")
        var mobileLoginFlag: Boolean? = null,

        @ApiModelProperty(name = "是否锁定")
        var accountLocked: Boolean? = null,

        @ApiModelProperty(name = "是否过期")
        var accountExpired: Boolean? = null,

        @ApiModelProperty(name = "0:个人，1：机构")
        var typeFlag: Int? = null,

        @ApiModelProperty(name = "账号等级标识")
        var levelId: String? = null,

        @ApiModelProperty(name = "人员标识")
        var staffId: String? = null,

        @ApiModelProperty(name = "组织标识")
        var organizationId: String? = null,

        @ApiModelProperty(name = "微信OpenId")
        var wxOpenid: String? = null,

        @ApiModelProperty(name = "支付宝OpenId")
        var alipayOpenid: String? = null,

        @ApiModelProperty(name = "头像")
        var avatar: String? = null,

        @ApiModelProperty(name = "状态")
        var status: Int? = null,

        @ApiModelProperty(name = "备注")
        var remark: String? = null,

        @ApiModelProperty(name = "创建人ID")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        var creatorName: String? = null,

        @ApiModelProperty(name = "修改时间")
        var updateTime: LocalDateTime? = null,

        @ApiModelProperty(name = "账号过期时间")
        var expiredDate: LocalDateTime? = null,

        @ApiModelProperty(name = "创建日期")
        var createTime: LocalDateTime? = null
)