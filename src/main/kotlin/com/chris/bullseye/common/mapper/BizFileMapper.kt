package com.chris.bullseye.common.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.BizFile
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Mapper
interface BizFileMapper: MPBaseMapper<BizFile> {

    fun getByFileHash(@Param("fileHash") fileHash:String):BizFile
}