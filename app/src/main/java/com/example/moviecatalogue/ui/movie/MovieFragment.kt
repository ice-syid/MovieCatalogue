package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.vo.Status
import org.koin.android.ext.android.inject

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by inject<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> stateLoading(true)
                        Status.SUCCESS -> {
                            stateLoading(false)
                            movies.data?.results?.let { movieAdapter.setMovies(it) }
                        }
                        Status.ERROR -> {
                            stateLoading(false)
                        }
                    }
                }
            })

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