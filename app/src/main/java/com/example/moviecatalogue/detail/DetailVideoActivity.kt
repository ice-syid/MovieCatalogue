package com.example.moviecatalogue.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.core.vo.Status
import com.example.moviecatalogue.databinding.ActivityDetailVideoBinding
import org.koin.android.ext.android.inject

class DetailVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVideoBinding
    private val viewModel by inject<DetailVideoViewModel>()
    private lateinit var movie: MovieEntity
    private lateinit var tvShow: TvShowEntity
    private var extras: Bundle? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        extras = intent.extras
        if (extras != null) {
            val videoId = extras?.getInt(EXTRA_VIDEO)
            val videoType = extras?.getInt(EXTRA_TYPE)
            if (videoId != 0 && videoType != 0) {
                viewModel.setSelectedVideo(videoId, videoType)
                viewModel.getVideo(videoId)?.observe(this, {
                    when (val video = it.data) {
                        is MovieEntity -> {
                            movie = video
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
                            tvShow = video
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        val extras = intent.extras
        if (extras != null) {
            val videoId = extras.getInt(EXTRA_VIDEO)
            val videoType = extras.getInt(EXTRA_TYPE)
            if (videoId != 0 && videoType != 0) {
                viewModel.setSelectedVideo(videoId, videoType)
                viewModel.getVideo(videoId)?.observe(this, {
                    when (val video = it.data) {
                        is MovieEntity -> {
                            when (it.status) {
                                Status.LOADING -> stateLoading(true)
                                Status.SUCCESS -> {
                                    stateLoading(false)
                                    setFavoriteState(video.isFavorite)
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
                                    setFavoriteState(video.isFavorite)
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            val videoType = extras?.getInt(EXTRA_TYPE)
            if (videoType == 1) {
                viewModel.setFavoriteMovie(movie)
            } else {
                viewModel.setFavoriteTvShow(tvShow)
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_on)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_off)
        }
    }

    companion object {
        const val EXTRA_VIDEO = "extra_video"
        const val EXTRA_TYPE = "extra_type"
    }
}