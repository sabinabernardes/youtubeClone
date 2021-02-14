package com.binapp.youtubeclone.data.repository

import com.binapp.youtubeclone.data.model.ListVideo

interface VideoRepository {
    suspend fun getVideo():
            ListVideo?
}