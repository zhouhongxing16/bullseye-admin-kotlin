package com.chris.bullseye.system.controller

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.chris.bullseye.system.dto.MenuDto
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.Menu
import com.chris.bullseye.system.service.BaseService
import com.chris.bullseye.system.service.MenuService
import com.chris.bullseye.common.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date 2020-12-10 16:12
 */
@Api(tags = ["菜单管理"], produces = "菜单管理")
@OperationLog("菜单管理")
@RestController
@RequestMapping("/menu")
class MenuController(var menuService: MenuService)  {



    @OperationLog("获取所有菜单(树形)")
    @GetMapping("/getAllMenuByTree")
    @ApiOperation(value = "获取所有菜单(树形)", notes = "获取所有菜单(树形)")
    @ApiImplicitParam(name = "获取所有菜单(树形)", value = "")
    fun getAllMenuByTree(): JsonResult<Any> {
        val menuList = menuService.getAllMenus()
        return JsonResult.success(menuList, "获取成功")
    }

    @OperationLog("获取登录用户菜单")
    @GetMapping("/getMenusByAccountId")
    @ApiOperation(value = "获取登录用户菜单", notes = "获取登录用户菜单")
    @ApiImplicitParam(name = "获取登录用户菜单", value = "")
    fun getMenusByAccountId(): JsonResult<Any> {
        val menuList: List<MenuDto> = menuService.getMenusByAccountId()
        return JsonResult.success(menuList, "获取成功")
    }

    @OperationLog("根据角色获取菜单")
    @GetMapping("/getMenusByRoleId/{roleId}")
    @ApiOperation(value = "根据角色获取菜单", notes = "根据角色获取菜单")
    @ApiImplicitParam(name = "根据角色获取菜单", value = "")
    fun getMenusByRoleId(@PathVariable roleId: String): JsonResult<Any> {
        val menuList: List<MenuDto> = menuService.getMenusByRoleId(roleId)
        return JsonResult.success(menuList, "获取成功")
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: Menu): JsonResult<Menu> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return menuService.add(obj)
    }




    @OperationLog("(admin)分页获取一级菜单")
    @PostMapping("/getMenusByPage")
    @ApiOperation(value = "(admin)分页获取一级菜单", notes = "(admin)分页获取一级菜单")
    @ApiImplicitParam(name = "(admin", value = "")
    fun getMenusByPage(@RequestBody params: MutableMap<String, String?>): JsonResult<Any> {
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        if (params["isFirst"].isNullOrEmpty()) {
            params["isFirst"] = "true"
        }
        PageHelper.startPage<MenuDto>(params)
        var list = menuService.getDtoListByParams(params)
        var pageInfo = PageInfo(list)
        return JsonResult.success(pageInfo, "查询成功")
    }
}
