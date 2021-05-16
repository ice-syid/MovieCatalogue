package com.example.moviecatalogue.ui.favorite.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.databinding.ItemsTvShowBinding
import com.example.moviecatalogue.ui.detail.DetailVideoActivity

class FavoriteTvShowsAdapter :
    PagedListAdapter<TvShowEntity, FavoriteTvShowsAdapter.VideoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        getItem(position)?.let { tvShow ->
            holder.bind(tvShow)
        }
    }

    class VideoViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvVideoTitle.text = tvShow.originalName
                tvVideoYear.text = tvShow.firstAirDate
                tvVideoRating.text = tvShow.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w780" + tvShow.posterPath)
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}