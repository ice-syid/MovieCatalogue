package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.ui.detail.DetailVideoActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VideoViewHolder>() {
    private var listMovies = ArrayList<MovieResultsItem>()

    fun setMovies(movies: List<MovieResultsItem>?) {
        if (movies == null) return
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
        fun bind(movie: MovieResultsItem) {
            with(binding) {
                tvVideoTitle.text = movie.title
                tvVideoYear.text = movie.date
                tvVideoRating.text = movie.rating.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w780" + movie.poster)
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