package com.binapp.youtubeclone.data.repository

import com.binapp.youtubeclone.data.service.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepositoryImplementation: VideoRepository {

    override suspend fun getVideo()=
        withContext(Dispatchers.IO) {
            RemoteDataSource().getVideo()
        }
}