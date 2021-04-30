package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailVideoBinding

class DetailVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailVideoViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val videoId = extras.getInt(EXTRA_VIDEO)
            val videoType = extras.getInt(EXTRA_TYPE)
            if (videoId != 0 && videoType != 0) {
                viewModel.setSelectedVideo(videoId, videoType)
                val video = viewModel.getVideo()
                binding.contentDetailVideo.textTitle.text = video?.title
                binding.contentDetailVideo.textYear.text = video?.release_date
                binding.contentDetailVideo.textRating.text = video?.rating.toString()
                binding.contentDetailVideo.textOverview.text = video?.overview
                Glide.with(this)
                    .load(video?.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.contentDetailVideo.imagePoster)
            }
        }
    }

    companion object {
        const val EXTRA_VIDEO = "extra_video"
        const val EXTRA_TYPE = "extra_type"
    }
}