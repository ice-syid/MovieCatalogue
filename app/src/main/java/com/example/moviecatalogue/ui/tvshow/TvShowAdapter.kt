package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.databinding.ItemsTvShowBinding
import com.example.moviecatalogue.ui.detail.DetailVideoActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.VideoViewHolder>() {
    private var listTvShows = ArrayList<TvShowResultsItem>()

    fun setTvShows(tvShows: List<TvShowResultsItem>?) {
        if (tvShows == null) return
        listTvShows.clear()
        listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size

    class VideoViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowResultsItem) {
            with(binding) {
                tvVideoTitle.text = tvShow.title
                tvVideoYear.text = tvShow.date
                tvVideoRating.text = tvShow.rating.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w780" + tvShow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgVideoPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailVideoActivity::class.java).apply {
                        putExtra(DetailVideoActivity.EXTRA_VIDEO, tvShow.id)
                        putExtra(DetailVideoActivity.EXTRA_TYPE, 2)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }
}