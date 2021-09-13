package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.system.pojo.Department
import com.chris.bullseye.system.mapper.DepartmentMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 11:24
 */
@Service
class DepartmentService(var departmentMapper: DepartmentMapper):BaseService<Department>() {

    override fun getMapper(): BaseMapper<Department> {
        return departmentMapper
    }
}