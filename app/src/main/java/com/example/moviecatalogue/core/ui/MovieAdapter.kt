package com.example.moviecatalogue.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.detail.DetailVideoActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VideoViewHolder>() {
    private var listMovies = mutableListOf<MovieEntity>()

    fun setMovies(movies: MutableList<MovieEntity>) {
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class VideoViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvVideoTitle.text = movie.originalTitle
                tvVideoYear.text = movie.releaseDate
                tvVideoRating.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w780" + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgVideoPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailVideoActivity::class.java).apply {
                        putExtra(DetailVideoActivity.EXTRA_VIDEO, movie.id)
                        putExtra(DetailVideoActivity.EXTRA_TYPE, 1)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }
}