package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.dto.AccountDto
import com.chris.bullseye.system.pojo.Account
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date2020 12 01 14:40
 */
@Mapper
interface AccountMapper: BaseMapper<Account> {

    fun getAccountByUserName(userName: String?): AccountDto?

    fun getAccountByStaffMobile(mobile: String?): AccountDto?

    fun getAccountByStaffId(mobile: String?): AccountDto?

    fun getById(id: String?): Account?

    fun getDtoListByParams(map: MutableMap<String, String?>): List<AccountDto>

    fun getNotInRoleCodeListByParams(map: MutableMap<String, String?>): List<AccountDto>

}