package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                Log.d("syid", tvShows.data?.results.toString())
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> stateLoading(true)
                        Status.SUCCESS -> {
                            stateLoading(false)
                            tvShows.data?.results?.let { tvShowAdapter.setTvShows(it) }
                        }
                        Status.ERROR -> {
                            stateLoading(false)
                        }
                    }
                }
            })

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