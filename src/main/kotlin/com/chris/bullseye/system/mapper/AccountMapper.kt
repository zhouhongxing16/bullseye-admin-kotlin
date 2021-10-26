package com.chris.bullseye.system.mapper

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.AccountDto
import com.chris.bullseye.system.entity.request.AccountRequest
import com.chris.bullseye.system.pojo.Account
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Chris
 * @date2020 12 01 14:40
 */
@Mapper
interface AccountMapper: MPBaseMapper<Account> {

    fun getAccountByUserName(userName: String?): AccountDto?

    fun getAccountByStaffMobile(mobile: String?): AccountDto?

    fun getAccountByStaffId(mobile: String?): AccountDto?

    fun getById(id: String?): Account?

    fun getDtoListByParams(map: MutableMap<String, String?>): List<AccountDto>

    fun getDtoListByPage(page: Page<AccountDto>,@Param("params") account:AccountRequest): Page<AccountDto>

    fun getNotInRoleCodeListByParams(map: MutableMap<String, String?>): List<AccountDto>

}