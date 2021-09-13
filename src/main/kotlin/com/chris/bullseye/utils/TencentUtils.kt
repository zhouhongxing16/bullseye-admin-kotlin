package com.chris.bullseye.utils

import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.common.exception.TencentCloudSDKException

import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosResponse

import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosRequest

import com.tencentcloudapi.vod.v20180717.VodClient

import com.tencentcloudapi.common.profile.ClientProfile

import com.tencentcloudapi.common.profile.HttpProfile
import com.tencentcloudapi.vod.v20180717.models.MediaInfo


/**
 * @author Chris
 * @date 2021-01-14 17:21
 */
class TencentUtils {
     companion object{
            //accountId = 100017340906
            private const val secretId:String = "AKIDLQMEBtefcHfngQyHLFuITR1MSIwKOSDD"
            private const val secretKey:String = "zh93lFTSGo685pKbO1YntrdCboxrGSZV"
             fun getVideoInfo(ids:Array<String>): MediaInfo? {
                 try {
                     val cred = Credential(secretId, secretKey)
                     val httpProfile = HttpProfile()
                     httpProfile.endpoint = "vod.tencentcloudapi.com"
                     val clientProfile = ClientProfile()
                     clientProfile.httpProfile = httpProfile
                     val client = VodClient(cred, "", clientProfile)
                     val req = DescribeMediaInfosRequest()
                     req.fileIds = ids
                     val resp = client.DescribeMediaInfos(req)
                     println(DescribeMediaInfosResponse.toJsonString(resp))
                     if(resp.mediaInfoSet.isNullOrEmpty()){
                         return null
                     }else{
                         return resp.mediaInfoSet[0]
                     }
                 } catch (e: TencentCloudSDKException) {
                     println(e.toString())
                     return null
                 }
             }
     }
}