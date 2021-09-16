package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:51
 * 员工
 */
@Table(name = "b_staff")
open class Staff (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "ID")
        var id: String? = null,

        @ApiModelProperty(name = "工号")
        @Column(name = "serial_no")
        var serialNo: String? = null,

        @ApiModelProperty(name = "姓名")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "照片")
        @Column(name = "img")
        var img: String? = null,

        @ApiModelProperty(name = "性别")
        @Column(name = "gender_id")
        var genderId: String? = null,

        @ApiModelProperty(name = "电话号码")
        @Column(name = "mobile")
        var mobile: String? = null,

        @ApiModelProperty(name = "邮箱")
        @Column(name = "email")
        var email: String? = null,

        @ApiModelProperty(name = "所属组织")
        @Column(name = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "所属部门")
        @Column(name = "department_id")
        var departmentId: String? = null,

        @ApiModelProperty(name = "所属专业")
        @Column(name = "major_id")
        var majorId: String? = null,

        @ApiModelProperty(name = "生日")
        @Column(name = "birthday")
        var birthday: String? = null,

        @ApiModelProperty(name = "学位")
        @Column(name = "academic_id")
        var academicId: String? = null,

        @ApiModelProperty(name = "学历")
        @Column(name = "degree_id")
        var degreeId: String? = null,

        @ApiModelProperty(name = "职位")
        @Column(name = "position_id")
        var positionId: String? = null,

        @ApiModelProperty(name = "职称")
        @Column(name = "title_id")
        var titleId: String? = null,

        @ApiModelProperty(name = "人员类型")
        @Column(name = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "证件类型")
        @Column(name = "identify_type_id")
        var identifyTypeId: String? = null,

        @ApiModelProperty(name = "证件号码")
        @Column(name = "identify_no")
        var identifyNo: String? = null,

        @ApiModelProperty(name = "所属省份")
        @Column(name = "province_id")
        var provinceId: String? = null,

        @ApiModelProperty(name = "所属城市")
        @Column(name = "city_id")
        var cityId: String? = null,

        @ApiModelProperty(name = "政治面貌")
        @Column(name = "policy")
        var policy: String? = null,

        @ApiModelProperty(name = "民族")
        @Column(name = "nation_id")
        var nationId: String? = null,

        @ApiModelProperty(name = "入职日期")
        @Column(name = "join_date")
        var joinDate: String? = null,

        @ApiModelProperty(name = "备注")
        @Column(name = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,


        @ApiModelProperty(name = "创建人ID")
        @Column(name = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "create_time")
        var createTime: LocalDateTime? = null,


        )