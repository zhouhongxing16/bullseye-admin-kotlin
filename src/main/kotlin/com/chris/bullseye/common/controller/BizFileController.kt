package com.chris.bullseye.common.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.BizFile
import com.chris.bullseye.common.service.BizFileService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


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
) {


    @PostMapping("/uploadFiles")
    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    fun upload(files: Array<MultipartFile>): JsonResult<BizFile> {
        return bizFileService.upload(files)
    }

    @PostMapping("/uploadSingleFile")
    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    fun uploadSingleFile(@RequestParam("file") file: MultipartFile): JsonResult<BizFile> {
        return bizFileService.uploadSingleFile(file)
    }

    @PostMapping("/removeFile/{path}")
    @ApiOperation(value = "删除文件", notes = "删除文件")
    fun removeFile(@PathVariable path: String): JsonResult<BizFile> {
        return bizFileService.removeFile(path)
    }
}