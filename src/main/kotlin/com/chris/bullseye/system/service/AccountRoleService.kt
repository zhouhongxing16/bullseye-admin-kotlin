package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.mapper.AccountRoleMapper
import com.chris.bullseye.system.pojo.AccountRole
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 16:07
 */
@Service
class AccountRoleService(var accountRoleMapper: AccountRoleMapper):BaseService<AccountRole>() {
    override fun getMapper(): MPBaseMapper<AccountRole> {
        return accountRoleMapper
    }

    fun deleteAdminByAccountId(map: MutableMap<String, String?>): Int {
        return accountRoleMapper.deleteAdminByAccountId(map)
    }

    fun deleteByAccountRoleId(map: MutableMap<String, String?>): Int {
        return accountRoleMapper.deleteByAccountRoleId(map)
    }


    fun saveAccountRoles(accountId:String,list:List<String>): JsonResult<Any> {
        val result = JsonResult<Any>()
        if (StringUtils.isEmpty(accountId)) {
            result.success = false
            result.message = "accountId不能为空！"
        } else {
            val count: Int = accountRoleMapper.deleteByAccountId(accountId)
            if (list.isNullOrEmpty()) {
                result.message  ="角色清除成功！"
            } else {
                var accountRole: AccountRole? = null
                for (id in list) {
                    accountRole = AccountRole()
                    accountRole.accountId=accountId
                    accountRole.roleId = id
                    accountRole.id = null
                    accountRoleMapper.insert(accountRole)
                }
                result.message = "角色授权成功！"
            }
            result.success  =true
        }
        return result
    }
}