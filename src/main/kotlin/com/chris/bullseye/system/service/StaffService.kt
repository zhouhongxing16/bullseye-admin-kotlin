package com.chris.bullseye.system.service

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.response.StaffResponse
import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.mapper.AccountMapper
import com.chris.bullseye.system.mapper.StaffMapper
import com.chris.bullseye.system.pojo.Account
import com.chris.bullseye.system.pojo.Staff
import com.chris.bullseye.common.utils.AuthUtil
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date2020 12 07 16:55
 */
@Service
class StaffService(var staffMapper: StaffMapper, var accountMapper: AccountMapper) : BaseService<Staff>() {
    override fun getMapper(): MPBaseMapper<Staff> {
        return staffMapper
    }

    fun getStaffInfoByStaffId(staffId:String):Staff{
        var staff  = staffMapper.selectOne(Wrappers.lambdaQuery<Staff?>().eq(Staff::id,staffId))
        return staff
    }

    /*fun createDto(dto: StaffResponse): JsonResult<StaffResponse> {
        var user = AuthUtil.getCurrentUser()
        dto.creatorId = user!!.id
        dto.creatorName = user!!.name
        var result = JsonResult<StaffResponse>()
        result.status = HttpStatus.OK.value()
        var staff = staffMapper.selectOne(Wrappers.lambdaQuery<Staff?>().eq(Staff::serialNo,dto.serialNo))

        if (staff != null) {
            return result.failed("该工号已存在！")
        } else {
            if (dto.addAccountFlag) {
                var act = accountMapper.getAccountByUserName(dto.serialNo)
                if (act != null) {
                    return result.failed("该账号已存在！")
                }
            }
            dto.id=null
            var status = getMapper().insert(dto)
            if (status > 0) {
                result.success(dto, "操作成功！")
                if (dto.addAccountFlag) {
                    var act = accountMapper.getAccountByUserName(dto.serialNo)
                    var status = createAccount(dto)
                    if (status == 2) {
                        result.success = false
                        result.message = "该账号不可用！"
                    } else if (status > 0) {
                        result.success = true
                    } else {

                    }
                }
                return result
            } else {
                return result.failed("操作失败！")
            }
        }

    }*/

    fun createAccount(dto: StaffResponse): Int {
        var account = Account()
        account.username = dto.serialNo
        val password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(dto.serialNo)
        account.password = password
        account.accountLocked = false
        account.accountExpired = false
        account.typeFlag = 1
        account.nickName = dto.name
        account.status = 1
        account.staffId = dto.id
        account.id=null
        return accountMapper.insert(account)
    }
}