package com.binapp.youtubeclone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.binapp.youtubeclone.R
import com.binapp.youtubeclone.data.model.ListVideo
import com.binapp.youtubeclone.data.model.Video
import com.binapp.youtubeclone.data.repository.VideoRepositoryImplementation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videos:MutableList<Video> = mutableListOf<Video>()

        videoAdapter = VideoAdapter(videos){ video->
            println(video)
        }
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter= videoAdapter

        VideoRepositoryImplementation().getVideo()

        CoroutineScope(Dispatchers.IO).launch{
            val res: Deferred<ListVideo?> = async {VideoRepositoryImplementation().getVideo()}
            val listVideo:ListVideo?= res.await()
            withContext(Dispatchers.Main) {
                listVideo?.let{
                    videos.clear()
                    videos.addAll(listVideo.data)
                    videoAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE

                }
            }
        }
    }
}