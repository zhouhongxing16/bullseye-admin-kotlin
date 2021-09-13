package com.chris.bullseye.entity.response

/**
 * @author Chris
 * @date 2021-01-08 11:12
 */
data class AliVideoResponse(
        var requestId: String? = null,
        var playInfoList: PlayInfoList? = null,
        var VideoBase: VideoBase? = null,
)

data class PlayInfoList(
        var width: Int? = null,
        var height: Int? = null,
        var size: Int? = null,
        var playURL: String? = null,
        var bitrate: String? = null,
        var definition: String? = null,
        var duration: String? = null,
        var format: String? = null,
        var fps: String? = null,
        var encrypt: String? = null,
        var streamType: String? = null,
        var jobId: String? = null,
        var preprocessStatus: String? = null,
        var status: String? = null,
        var creationTime: String? = null,
        var modificationTime: String? = null,
        var narrowBandType: String? = null,
        var specification: String? = null,
)

data class VideoBase(
        var outputType: String? = null,
        var coverURL: String? = null,
        var duration: String? = null,
        var status: String? = null,
        var title: String? = null,
        var videoId: String? = null,
        var mediaType: String? = null,
        var creationTime: String? = null,
        var transcodeMode: String? = null,
        var thumbnailList: String? = null,
)
