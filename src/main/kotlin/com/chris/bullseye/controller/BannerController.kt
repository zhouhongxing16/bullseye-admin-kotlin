package com.chris.bullseye.controller

import com.chris.bullseye.pojo.Banner
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.CourseCategory
import com.chris.bullseye.pojo.Menu
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.BannerService
import org.springframework.beans.factory.annotation.Autowired
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
        jsonResult: JsonResult<Banner>
) : BaseController<Banner>(jsonResult) {
         override fun service(): BaseService<Banner> {
        return bannerService
    }


    @OperationLog("获取首页banner")
    @GetMapping("/getWebBanners")
    @ApiOperation(value = "获取首页banner", notes = "获取首页banner")
    @ApiImplicitParam(name = "获取首页banner", value = "")
    fun getWebBanners(): JsonResult<Banner>{
        val map: MutableMap<String, String?> = HashMap(2)
        map["status"] = "1"
        map["limit"] = "5"
        var bannerList = bannerService.getListByParams(map)
        return jsonResult.successList(bannerList,"查询成功！")
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: Banner): JsonResult<Banner> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }
}