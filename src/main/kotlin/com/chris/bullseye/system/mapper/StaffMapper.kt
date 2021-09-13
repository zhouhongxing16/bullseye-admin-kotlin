package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.pojo.Staff
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date2020 12 07 16:56
 */
@Mapper
interface StaffMapper: BaseMapper<Staff>  {

}