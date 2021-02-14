package com.binapp.youtubeclone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.binapp.youtubeclone.R
import com.binapp.youtubeclone.data.model.ListVideo
import com.binapp.youtubeclone.data.model.ResultVideo
import com.binapp.youtubeclone.data.model.Video
import com.binapp.youtubeclone.data.repository.VideoRepositoryImplementation
import com.binapp.youtubeclone.presentation.viewmodel.VideosViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoAdapter
    private val videos:MutableList<Video> = mutableListOf<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewConfig()

        val viewModel = ViewModelProvider(this,
            VideosViewModel.VideosViewModelFactory(VideoRepositoryImplementation()))
            .get(VideosViewModel::class.java)


        viewModel.getVideo()

        viewModel.videos.observe(this, Observer { videos ->

            videos?.let {

                when (it) {
                    is ResultVideo.AddVideo -> addVideo(it.resultVideoList)
                    is ResultVideo.SetErroDispay-> setErroDispay()

                }
            }
        })

    }
    private fun recyclerViewConfig(){

        videoAdapter = VideoAdapter(videos){ video->
            println(video)
        }
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter= videoAdapter
    }
    private fun addVideo(videosAdd: ListVideo?) {
        videos.clear()
        if (videosAdd != null) {
            videos.addAll(videosAdd.data)
        }
        videoAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
        //videoAdapter.videos = videosAdd
    }
    private fun setErroDispay() {
        progressBar.visibility = View.GONE


        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }

}