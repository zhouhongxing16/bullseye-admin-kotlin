package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2021-01-06 17:04
 */
data class StaffResponse(

        @ApiModelProperty(name = "专业")
        var majorName: String? = null,


        @ApiModelProperty(name = "组织")
        var organizationName: String? = null,

        @ApiModelProperty(name = "职称")
        var titleName : String? = null,


        @ApiModelProperty(name = "ID")
        var id: String? = null,

        @ApiModelProperty(name = "工号")
        var serialNo: String? = null,

        @ApiModelProperty(name = "姓名")
        var name: String? = null,

        @ApiModelProperty(name = "照片")
        var img: String? = null,

        @ApiModelProperty(name = "性别")
        var genderId: String? = null,

        @ApiModelProperty(name = "电话号码")
        var mobile: String? = null,

        @ApiModelProperty(name = "邮箱")
        var email: String? = null,

        @ApiModelProperty(name = "所属组织")
        var organizationId: String? = null,

        @ApiModelProperty(name = "所属部门")
        var departmentId: String? = null,

        @ApiModelProperty(name = "所属专业")
        var majorId: String? = null,

        @ApiModelProperty(name = "生日")
        var birthday: String? = null,

        @ApiModelProperty(name = "学位")
        var academicId: String? = null,

        @ApiModelProperty(name = "学历")
        var degreeId: String? = null,

        @ApiModelProperty(name = "职位")
        var positionId: String? = null,

        @ApiModelProperty(name = "职称")
        var titleId: String? = null,

        @ApiModelProperty(name = "人员类型")
        var typeId: String? = null,

        @ApiModelProperty(name = "证件类型")
        var identifyTypeId: String? = null,

        @ApiModelProperty(name = "证件号码")
        var identifyNo: String? = null,

        @ApiModelProperty(name = "所属省份")
        var provinceId: String? = null,

        @ApiModelProperty(name = "所属城市")
        var cityId: String? = null,

        @ApiModelProperty(name = "政治面貌")
        var policy: String? = null,

        @ApiModelProperty(name = "民族")
        var nationId: String? = null,

        @ApiModelProperty(name = "入职日期")
        var joinDate: String? = null,

        @ApiModelProperty(name = "备注")
        var remark: String? = null,

        @ApiModelProperty(name = "状态")
        var status: Int? = null,


        @ApiModelProperty(name = "创建人ID")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        var createTime: LocalDateTime? = null,

)