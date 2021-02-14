package com.binapp.youtubeclone.data.model

sealed class ResultVideo{
    data class AddVideo(val resultVideoList: ListVideo?): ResultVideo()
    data class SetErroDispay(val error : Boolean): ResultVideo()
}
