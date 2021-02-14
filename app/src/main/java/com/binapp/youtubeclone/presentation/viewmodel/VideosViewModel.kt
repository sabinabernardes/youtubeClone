package com.binapp.youtubeclone.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.binapp.youtubeclone.data.model.ResultVideo
import com.binapp.youtubeclone.data.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideosViewModel(private val repository: VideoRepository) : ViewModel() {


    val videos = MutableLiveData<ResultVideo>()

    fun getVideo(){

        viewModelScope.launch(Dispatchers.Main)
        {
            try {
                val response = repository.getVideo()
                videos.value = ResultVideo.AddVideo(response)

            }catch(exception:Exception){
                videos.value = ResultVideo.SetErroDispay(error = true)
            }
        }
    }
    class VideosViewModelFactory(
            private val repository: VideoRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideosViewModel(repository) as T
        }

    }



}