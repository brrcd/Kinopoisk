package com.testapp.testkinopoisk.movielist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.MOVIE_DATA_TAG
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.base.BaseFragment
import com.testapp.testkinopoisk.databinding.FragmentMovieListBinding

class MovieListFragment :
	BaseFragment<FragmentMovieListBinding>(FragmentMovieListBinding::inflate) {
	
	private val viewModel: MovieListViewModel by viewModels()
	private val adapter = MovieListAdapter() { showMovieDetails(it) }
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.rvMovieList.adapter = adapter
		viewModel.apply {
			movieListData.observe(viewLifecycleOwner, ::updateMovieList)
		}
	}
	
	override fun onResume() {
		super.onResume()
		viewModel.getMovieList()
	}
	
	private fun updateMovieList(movieList: List<Movie>){
		adapter.updateList(movieList)
	}
	
	// передаём Movie объект, для этого добавляем Parcelable интерфейс
	private fun showMovieDetails(movie: Movie) {
		findNavController().navigate(R.id.movieDetailFragment, bundleOf(MOVIE_DATA_TAG to movie))
	}
}