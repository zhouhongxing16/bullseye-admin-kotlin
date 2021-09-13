package com.chris.bullseye.dto
import com.chris.bullseye.pojo.Course
import com.chris.bullseye.pojo.FamousTeacher
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 18:59
 */
data class FamousTeacherDto(
        var userName: String? = null,

        var teacherName: String? = null,

        var organizationName: String? = null,

        var titleName: String? = null,

        var majorName: String? = null,

        var img: String? = null,

        var courseList: List<Course>? = null,

        var serialNo : String ?=null,

        var mobile : String ? = null
) :FamousTeacher()