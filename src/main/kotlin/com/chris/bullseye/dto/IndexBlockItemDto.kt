package com.chris.bullseye.dto
import com.chris.bullseye.pojo.IndexBlockItem
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-04 16:50
 */
data class IndexBlockItemDto(
     var userName: String? = null,

     var courseName: String? = null,

     var lecturerName: String? = null

) :IndexBlockItem()