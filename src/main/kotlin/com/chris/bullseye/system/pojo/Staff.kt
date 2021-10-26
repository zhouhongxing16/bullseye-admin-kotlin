package com.chris.bullseye.system.pojo

import java.util.Date
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:51
 * 员工
 */
@TableName(value = "b_staff")
open class Staff (
        
        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "ID")
        var id: String? = null,

        @ApiModelProperty(name = "工号")
        @TableField(value = "serial_no")
        var serialNo: String? = null,

        @ApiModelProperty(name = "姓名")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "照片")
        @TableField(value = "img")
        var img: String? = null,

        @ApiModelProperty(name = "性别")
        @TableField(value = "gender_id")
        var genderId: String? = null,

        @ApiModelProperty(name = "电话号码")
        @TableField(value = "mobile")
        var mobile: String? = null,

        @ApiModelProperty(name = "邮箱")
        @TableField(value = "email")
        var email: String? = null,

        @ApiModelProperty(name = "所属组织")
        @TableField(value = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "所属部门")
        @TableField(value = "department_id")
        var departmentId: String? = null,

        @ApiModelProperty(name = "所属专业")
        @TableField(value = "major_id")
        var majorId: String? = null,

        @ApiModelProperty(name = "生日")
        @TableField(value = "birthday")
        var birthday: String? = null,

        @ApiModelProperty(name = "学位")
        @TableField(value = "academic_id")
        var academicId: String? = null,

        @ApiModelProperty(name = "学历")
        @TableField(value = "degree_id")
        var degreeId: String? = null,

        @ApiModelProperty(name = "职位")
        @TableField(value = "position_id")
        var positionId: String? = null,

        @ApiModelProperty(name = "职称")
        @TableField(value = "title_id")
        var titleId: String? = null,

        @ApiModelProperty(name = "人员类型")
        @TableField(value = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "证件类型")
        @TableField(value = "identify_type_id")
        var identifyTypeId: String? = null,

        @ApiModelProperty(name = "证件号码")
        @TableField(value = "identify_no")
        var identifyNo: String? = null,

        @ApiModelProperty(name = "所属省份")
        @TableField(value = "province_id")
        var provinceId: String? = null,

        @ApiModelProperty(name = "所属城市")
        @TableField(value = "city_id")
        var cityId: String? = null,

        @ApiModelProperty(name = "政治面貌")
        @TableField(value = "policy")
        var policy: String? = null,

        @ApiModelProperty(name = "民族")
        @TableField(value = "nation_id")
        var nationId: String? = null,

        @ApiModelProperty(name = "入职日期")
        @TableField(value = "join_date")
        var joinDate: String? = null,

        @ApiModelProperty(name = "备注")
        @TableField(value = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,


        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,


        )