package com.chris.bullseye.dto
import com.chris.bullseye.pojo.IndexBlock
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-04 16:49
 */
data class IndexBlockDto(
     var userName: String? = null,

     //用于返回
     var itemList:List<Any>? = null,

    //用于接收课程或者老师
    var list: List<String>? = null


) :IndexBlock() 