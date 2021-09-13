package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.dto.AccountDto
import com.chris.bullseye.pojo.Account
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date2020 12 01 14:40
 */
@Mapper
interface AccountMapper: BaseMapper<Account> {

    fun getAccountByUserName(userName: String?): Account?

    fun getAccountByStaffMobile(mobile: String?): Account?

    fun getById(id: String?): Account?

    fun getDtoListByParams(map: MutableMap<String, String?>): List<AccountDto>

    fun getNotInRoleCodeListByParams(map: MutableMap<String, String?>): List<AccountDto>

}