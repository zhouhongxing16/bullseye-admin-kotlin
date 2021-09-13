package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.Department
import org.apache.ibatis.annotations.Mapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 11:24
 */
@Mapper
interface DepartmentMapper: BaseMapper<Department> {
}