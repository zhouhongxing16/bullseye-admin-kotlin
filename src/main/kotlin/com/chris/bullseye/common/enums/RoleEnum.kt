package com.chris.bullseye.common.enums

/**
 * @author Chris
 * @date 2021-09-14 15:00
 */
enum class RoleEnum(name:String, var code:String, var desc:String) {

    /**
     * 角色默认枚举
     */
    SUPER_ADMIN ("超级管理员","SuperAdmin","超级管理员"),
    ORGANIZATION_ADMIN ("组织管理员","OrganizationAdmin","组织管理员"),
    DEPARTMENT_ADMIN ("部门管理员","DepartmentAdmin","部门管理员"),

}