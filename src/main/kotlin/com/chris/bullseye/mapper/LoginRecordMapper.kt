package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.LoginRecord
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020 12 07 17:01
 */

@Mapper
interface LoginRecordMapper : BaseMapper<LoginRecord> {

}