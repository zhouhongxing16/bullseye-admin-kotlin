package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.pojo.LoginRecord
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020 12 07 17:01
 */

@Mapper
interface LoginRecordMapper : BaseMapper<LoginRecord> {

}