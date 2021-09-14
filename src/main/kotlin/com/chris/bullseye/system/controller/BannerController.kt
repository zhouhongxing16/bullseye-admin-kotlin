package com.chris.bullseye.system.controller

import com.chris.bullseye.system.pojo.Banner
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.service.*
import com.chris.bullseye.common.utils.AuthUtil

import com.chris.bullseye.system.service.BannerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:24
 */
@Api(value = "轮播",tags = ["轮播"])
@OperationLog("轮播")
@RestController
@RequestMapping("/banner")
class BannerController(
        val bannerService: BannerService,
        var jsonResult: JsonResult<Banner>
) {


    @OperationLog("获取首页banner")
    @GetMapping("/getWebBanners")
    @ApiOperation(value = "获取首页banner", notes = "获取首页banner")
    @ApiImplicitParam(name = "获取首页banner", value = "")
    fun getWebBanners(): JsonResult<Banner>{
        val map: MutableMap<String, String?> = HashMap(2)
        map["status"] = "1"
        map["limit"] = "5"
        var bannerList = bannerService.getListByParams(map)
        return jsonResult.success(bannerList,"查询成功！")
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: Banner): JsonResult<Banner> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return bannerService.add(obj)
    }
}