package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.databinding.ActivityDetailVideoBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

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
                viewModel.getVideo(videoId)?.observe(this, { video ->
                    when (video) {
                        is MovieResultsItem -> {
                            with(binding.contentDetailVideo) {
                                this.textTitle.text = video.title
                                this.textDate.text = video.date
                                this.textRating.text = video.rating.toString()
                                this.textOverview.text = video.overview
                                Glide.with(applicationContext)
                                    .load("https://image.tmdb.org/t/p/w780" + video.poster)
                                    .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                            .error(R.drawable.ic_error)
                                    )
                                    .into(this.imagePoster)
                            }
                        }
                        is TvShowResultsItem -> {
                            with(binding.contentDetailVideo) {
                                this.textTitle.text = video.title
                                this.textDate.text = video.date
                                this.textRating.text = video.rating.toString()
                                this.textOverview.text = video.overview
                                Glide.with(applicationContext)
                                    .load("https://image.tmdb.org/t/p/w780" + video.poster)
                                    .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                            .error(R.drawable.ic_error)
                                    )
                                    .into(this.imagePoster)
                            }
                        }
                    }
                })
            }
        }
    }

    companion object {
        const val EXTRA_VIDEO = "extra_video"
        const val EXTRA_TYPE = "extra_type"
    }
}