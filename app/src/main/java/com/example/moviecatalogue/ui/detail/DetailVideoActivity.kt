package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.databinding.ActivityDetailVideoBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailVideoViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val videoId = extras.getInt(EXTRA_VIDEO)
            val videoType = extras.getInt(EXTRA_TYPE)
            if (videoId != 0 && videoType != 0) {
                viewModel.setSelectedVideo(videoId, videoType)
                viewModel.getVideo(videoId)?.observe(this, {
                    Log.d("syid", it.data.toString())
                    when (val video = it.data) {
                        is MovieEntity -> {
                            when (it.status) {
                                Status.LOADING -> stateLoading(true)
                                Status.SUCCESS -> {
                                    stateLoading(false)
                                    with(binding.contentDetailVideo) {
                                        this.textTitle.text = video.originalTitle
                                        this.textDate.text = video.releaseDate
                                        this.textRating.text = video.voteAverage.toString()
                                        this.textOverview.text = video.overview
                                        Glide.with(applicationContext)
                                            .load("https://image.tmdb.org/t/p/w780" + video.posterPath)
                                            .apply(
                                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                    .error(R.drawable.ic_error)
                                            )
                                            .into(this.imagePoster)
                                    }
                                }
                                Status.ERROR -> {
                                    stateLoading(false)
                                }
                            }
                        }
                        is TvShowEntity -> {
                            when (it.status) {
                                Status.LOADING -> stateLoading(true)
                                Status.SUCCESS -> {
                                    stateLoading(false)
                                    with(binding.contentDetailVideo) {
                                        this.textTitle.text = video.originalName
                                        this.textDate.text = video.firstAirDate
                                        this.textRating.text = video.voteAverage.toString()
                                        this.textOverview.text = video.overview
                                        Glide.with(applicationContext)
                                            .load("https://image.tmdb.org/t/p/w780" + video.posterPath)
                                            .apply(
                                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                    .error(R.drawable.ic_error)
                                            )
                                            .into(this.imagePoster)
                                    }
                                }
                                Status.ERROR -> {
                                    stateLoading(false)
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    private fun stateLoading(state: Boolean) {
        if (state) {
            binding.contentDetailVideo.progressBar.visibility = View.VISIBLE
        } else {
            binding.contentDetailVideo.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val EXTRA_VIDEO = "extra_video"
        const val EXTRA_TYPE = "extra_type"
    }
}