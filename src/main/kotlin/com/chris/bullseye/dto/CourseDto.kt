package com.chris.bullseye.dto
import com.chris.bullseye.entity.response.CourseScoreResponse
import com.chris.bullseye.entity.response.StaffResponse
import com.chris.bullseye.pojo.Course

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:27
 */
data class CourseDto(
     var userName: String? = null,

     var categoryName: String? = null,

     var organizationName: String? = null,

     var lecturerName: String? = null,

     var majorName: String? = null,

     var chapterList:List<CourseChapterDto>? = null,

     var totalStudyNum: Int = 0,

     var recentStudyNum: Int = 0,

     var subscribeFlag: Boolean = false,

     var commentFlag: Boolean? = false,

     var lecturer: StaffResponse? = null,

     var scoreList:List<CourseScoreResponse>? = null
) :Course()