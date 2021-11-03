package com.chris.bullseye.system.mapper

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.request.AccountRequest
import com.chris.bullseye.system.dto.response.AccountResponse
import com.chris.bullseye.system.pojo.Account
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Chris
 * @date2020 12 01 14:40
 */
@Mapper
interface AccountMapper: MPBaseMapper<Account> {

    fun getAccountByUserName(userName: String?): Account?

    fun getAccountByStaffMobile(mobile: String?): Account?

    fun getAccountByStaffId(mobile: String?): AccountResponse?

    fun getById(id: String?): Account?

    fun getDtoListByParams(map: MutableMap<String, String?>): List<AccountResponse>

    fun getDtoListByPage(page: Page<AccountResponse>,@Param("params") account:AccountRequest): Page<AccountResponse>

    fun getNotInRoleCodeListByParams(map: MutableMap<String, String?>): List<AccountResponse>

}