package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.AccountRole
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author Chris
 * @date 2020-12-10 16:06
 */
@Mapper
interface AccountRoleMapper:MPBaseMapper<AccountRole> {

    fun deleteAdminByAccountId(map: MutableMap<String, String?>): Int

    fun deleteByAccountRoleId(map: MutableMap<String, String?>): Int

    fun deleteByAccountId(@Param("accountId") accountId: String?): Int

}