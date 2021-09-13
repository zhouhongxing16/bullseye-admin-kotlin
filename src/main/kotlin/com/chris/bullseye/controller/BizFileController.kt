package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.BizFile
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.BizFileService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException


/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-12 22:24
 * 业务文件
 */
@Api(value = "业务文件",tags = ["业务文件"])
@OperationLog("业务文件")
@RestController
@RequestMapping("/bizfile")
class BizFileController(
        val bizFileService: BizFileService,
        jsonResult: JsonResult<BizFile>
) : BaseController<BizFile>(jsonResult) {
    override fun service(): BaseService<BizFile> {
        return bizFileService
    }


    @PostMapping("/uploadFiles")
    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    @ApiImplicitParam(name = "files", value = "")
    fun upload(files: Array<MultipartFile>): JsonResult<BizFile> {
        return bizFileService.upload(files)
    }

    @PostMapping("/uploadSingleFile")
    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    @ApiImplicitParam(name = "file", value = "")
    fun uploadSingleFile(@RequestParam("file") file: MultipartFile): JsonResult<BizFile> {
        return bizFileService.uploadSingleFile(file)
    }

    @PostMapping("/removeFile/{path}")
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParam(name = "path", value = "文件名")
    fun removeFile(@PathVariable path: String): JsonResult<BizFile> {
        return bizFileService.removeFile(path)
    }
}