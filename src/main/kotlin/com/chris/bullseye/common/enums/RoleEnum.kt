package com.chris.bullseye.common.enums

/**
 * @author Chris
 * @date 2021-09-14 15:00
 */
enum class RoleEnum(name:String, var code:String, var desc:String) {

    /**
     * 角色默认枚举
     */
    SUPERADMIN ("超级管理员","SuperAdmin","超级管理员"),
    ORGANIZATIONADMIN ("组织管理员","OrganizationAdmin","组织管理员"),
    DEPARTMENTADMIN ("部门管理员","DepartmentAdmin","部门管理员"),

}