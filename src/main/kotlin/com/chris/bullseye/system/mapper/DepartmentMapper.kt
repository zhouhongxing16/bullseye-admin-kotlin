package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.Department
import org.apache.ibatis.annotations.Mapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 11:24
 */
@Mapper
interface DepartmentMapper: MPBaseMapper<Department> {
}