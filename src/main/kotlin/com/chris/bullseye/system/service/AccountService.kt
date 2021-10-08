package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.dto.AccountDto
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.mapper.AccountMapper
import com.chris.bullseye.system.pojo.Account
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.Logger
import com.chris.bullseye.common.utils.ValidateCodeUtils
import com.chris.bullseye.system.entity.request.LoginRequest
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.interceptor.TransactionAspectSupport

/**
 * @author Chris
 * @date2020 12 01 14:41
 */
@Service
class AccountService(val accountMapper: AccountMapper) : BaseService<Account>(){

    override fun getMapper(): BaseMapper<Account> {
        return accountMapper
    }


    fun getAccountByUserName(userName: String?): AccountDto? {
        return accountMapper.getAccountByUserName(userName)
    }

    fun getAccountByStaffMobile(userName: String?): AccountDto? {
        return accountMapper.getAccountByStaffMobile(userName)
    }


    fun getDtoListByParams(map: MutableMap<String, String?>): List<AccountDto> {
        return accountMapper.getDtoListByParams(map)
    }
    fun getNotInRoleCodeListByParams(map: MutableMap<String, String?>): List<AccountDto> {
        return accountMapper.getNotInRoleCodeListByParams(map)
    }

    fun resetPassword(accountId: String?): JsonResult<Any> {
        var result = JsonResult<Any>()
        var account = accountMapper.getById(accountId)
        if(account==null){
            return result.failed("该账号不存在")
        }else{
            var act = Account()
            act.id = account.id
            var newPwd  = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(account.username)
            act.password = newPwd
            var status =  accountMapper.updateByPrimaryKeySelective(act)
            if(status>0){
                return result.success(null,"重置成功！")
            }else{
                return result.failed("重置失败！")
            }
        }
    }

    fun changePassword(obj:LoginRequest): JsonResult<Any> {
        var result = JsonResult<Any>()
        result.success  = false
        val user = AuthUtil.getCurrentUser()
        if (user == null) {
            result.message  = "未登录，非法访问！"
        } else if (obj.oldPassword.isNullOrEmpty()) {
            result.message = "旧密码不能为空"
        } else if (obj.newPassword.isNullOrEmpty()) {
            result.message = "新密码不能为空"
        } else {
            val accountDto  = accountMapper.getById(user.id)
            if (accountDto != null) {
                if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(obj.oldPassword, accountDto.password)) {
                    result.message = "密码验证错误！"
                } else {
                    var newPwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.newPassword)
                    val account = Account()
                    account.id = accountDto.id
                    account.password = newPwd
                    val count: Int = accountMapper.updateByPrimaryKeySelective(account)
                    if (count > 0) {
                        result.success  =true
                        result.message = "密码修改成功！"
                        result.status  = HttpStatus.OK.value()
                    }
                }
            } else {
                result.message = "非法请求！"
            }
        }
        return result
    }


    @Transactional(rollbackFor = [Exception::class])
    fun forgetPassword(obj: LoginRequest): JsonResult<Any> {
        val result = JsonResult<Any>()
        result.success  = false
        if (obj.mobile.isNullOrEmpty()) {
            result.message  ="手机号不能为空！"
        } else if (obj.password.isNullOrEmpty()) {
            result.message  ="密码不能为空！"
        } else if (obj.captcha.isNullOrEmpty()) {
            result.message  ="验证码不能为空！"
        } else if (obj.captcha != ValidateCodeUtils.getValidateCode(obj.mobile)) {
            result.message  ="验证码错误！"
        } else {
            val account = accountMapper.getAccountByStaffMobile(obj.mobile)
            if (account != null) {
                val pwd: String = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.password)
                val a = Account()
                a.id = account.id
                a.password  =pwd
                accountMapper.updateByPrimaryKeySelective(a)
                result.success  = true
                result.message  ="密码修改成功"
                //验证后删除验证码
                ValidateCodeUtils.removeValidateCode()
            } else {
                result.message  ="数据异常，非法请求！"
            }
            if (!result.success) {
                Logger.debug("数据有误，执行手动回滚！")
                //手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
            }
        }
        result.status = HttpStatus.OK.value()
        return result
    }
}