package com.binapp.youtubeclone.data.service

import com.binapp.youtubeclone.data.model.ListVideo
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class RemoteDataSource {

    private fun getVideo():ListVideo?{
        val client : OkHttpClient =OkHttpClient.Builder()
            .build()

        val request: Request = Request.Builder()
            .get()
            .url("https://tiagoaguiar.co/api/youtube-videos")
            .build()
        return try {
            val response: Response =client.newCall(request).execute()
            if (response.isSuccessful){

                GsonBuilder().create().fromJson(response.body()?.string(), ListVideo::class.java)

            }else{
                null
            }

        }catch (e: Exception) {
            null
        }
    }
}