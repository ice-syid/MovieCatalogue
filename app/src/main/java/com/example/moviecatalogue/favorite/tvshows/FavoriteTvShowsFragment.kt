package com.example.moviecatalogue.favorite.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentFavoriteTvShowsBinding
import com.example.moviecatalogue.favorite.FavoriteViewModel
import com.example.moviecatalogue.core.ui.FavoriteTvShowsAdapter
import org.koin.android.ext.android.inject

class FavoriteTvShowsFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteTvShowsBinding
    private val viewModel by inject<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        stateLoading(true)
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = FavoriteTvShowsAdapter()
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner) { tvShows ->
                if (tvShows != null) {
                    tvShowAdapter.submitList(tvShows)
                    stateLoading(false)
                }
            }

            binding.rvTvShow.layoutManager = LinearLayoutManager(context)
            binding.rvTvShow.setHasFixedSize(true)
            binding.rvTvShow.adapter = tvShowAdapter
        }
    }

    private fun stateLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}