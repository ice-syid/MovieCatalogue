package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.VideoEntity
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.ui.detail.DetailVideoActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VideoViewHolder>() {
    private var listMovies = ArrayList<VideoEntity>()

    fun setMovies(movies: List<VideoEntity>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
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
        fun bind(movie: VideoEntity) {
            with(binding) {
                tvVideoTitle.text = movie.title
                tvVideoYear.text = movie.release_date
                Glide.with(itemView.context)
                    .load(movie.poster)
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