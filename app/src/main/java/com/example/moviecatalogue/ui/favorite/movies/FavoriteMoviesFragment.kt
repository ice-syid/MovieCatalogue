package com.example.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentFavoriteMoviesBinding
import com.example.moviecatalogue.ui.favorite.FavoriteViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        stateLoading(true)
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            val movieAdapter = FavoriteMoviesAdapter()
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    movieAdapter.submitList(movies)
                    stateLoading(false)
                }
            }

            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = movieAdapter
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