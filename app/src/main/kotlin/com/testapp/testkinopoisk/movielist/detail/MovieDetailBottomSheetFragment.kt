package com.testapp.testkinopoisk.movielist.detail

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import coil.load
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.MOVIE_DATA_TAG
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.base.BaseBottomSheetFragment
import com.testapp.testkinopoisk.databinding.FragmentMovieDetailBinding
import com.testapp.testkinopoisk.movielist.MovieListViewModel
import com.testapp.testkinopoisk.parcelable

class MovieDetailBottomSheetFragment :
	BaseBottomSheetFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {
	
	// использование viewModel из нав графа, Jetpack Navigation
	private val viewModel: MovieListViewModel by navGraphViewModels(R.id.movieListFragment)
	
	private val movie: Movie
		get() = requireArguments().parcelable(MOVIE_DATA_TAG) ?: Movie()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setRootHeight()
		binding.txtViewTitle.text = movie.name
		binding.txtViewDescription.text = movie.description
		binding.imgViewBackdrop.load(movie.backdrop.url.ifEmpty { movie.poster.url })
		binding.btnRemoveMovieFromList.setOnClickListener {
			viewModel.removeMovieFromListById(movie.id)
		}
		viewModel.onMovieRemoved.observe(viewLifecycleOwner) { if (it) this.dismiss()}
	}
	
	// задаём максимальную высоту bottom sheet'а, setExpanded задаётся в базовом bottom sheet'е
	private fun setRootHeight() {
		val statusBarResourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
		val statusBarHeight = resources.getDimensionPixelSize(statusBarResourceId)
		
		binding.root.minHeight = (
				Resources.getSystem().displayMetrics.heightPixels - statusBarHeight)
	}
}