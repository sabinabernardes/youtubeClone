package com.binapp.youtubeclone.data.repository

import com.binapp.youtubeclone.data.service.RemoteDataSource

class VideoRepositoryImplementation {

    fun getVideo(){
        RemoteDataSource().getVideo()
    }
}