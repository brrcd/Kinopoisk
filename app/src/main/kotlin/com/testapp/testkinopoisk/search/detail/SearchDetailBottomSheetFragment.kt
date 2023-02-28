package com.testapp.testkinopoisk.search.detail

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import coil.load
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.MOVIE_DATA_TAG
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.base.BaseBottomSheetFragment
import com.testapp.testkinopoisk.databinding.FragmentSearchDetailBinding
import com.testapp.testkinopoisk.parcelable
import com.testapp.testkinopoisk.search.SearchViewModel

class SearchDetailBottomSheetFragment :
	BaseBottomSheetFragment<FragmentSearchDetailBinding>(FragmentSearchDetailBinding::inflate) {
	
	private val viewModel: SearchViewModel by navGraphViewModels(R.id.searchFragment)
	
	private val movie: Movie
		get() = requireArguments().parcelable(MOVIE_DATA_TAG) ?: Movie()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setRootHeight()
		binding.txtViewTitle.text = movie.name
		binding.txtViewDescription.text = movie.description
		binding.txtViewYear.text = "${movie.year}"
		binding.txtGenres.text = movie.genres.joinToString(separator = ", ") { it.name }
		binding.imgViewBackdrop.load(movie.backdrop.url.ifEmpty { movie.poster.url })
		binding.btnAddMovieToList.setOnClickListener {
			viewModel.saveMovieToList(movie)
			it.isEnabled = false
		}
	}
	
	private fun setRootHeight() {
		val statusBarResourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
		val statusBarHeight = resources.getDimensionPixelSize(statusBarResourceId)

		binding.root.minHeight = (
				Resources.getSystem().displayMetrics.heightPixels - statusBarHeight)
	}
}