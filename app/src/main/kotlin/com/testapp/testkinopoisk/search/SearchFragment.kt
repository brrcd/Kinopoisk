package com.testapp.testkinopoisk.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.testapp.repository.model.Movie
import com.testapp.repository.model.request.Filter
import com.testapp.testkinopoisk.MOVIE_DATA_TAG
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.base.BaseFragment
import com.testapp.testkinopoisk.databinding.FragmentSearchBinding
import com.testapp.testkinopoisk.search.filter.FilterDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
	
	private val viewModel: SearchViewModel by viewModels()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		viewModel.apply {
			movieLiveData.observe(viewLifecycleOwner, ::showMovieDetails)
			filterLiveData.observe(viewLifecycleOwner, ::setupFilterButton)
		}
		
		binding.editTxtInput.setOnEditorActionListener { _, actionId, _ ->
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				viewModel.searchByName(binding.editTxtInput.text.toString())
			}
			false
		}
		
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.pagingFlow.collectLatest {
					if (it != null) {
						setupSearchAdapter(it)
					}
				}
			}
		}
	}
	
	private fun setupSearchAdapter(data: PagingData<Movie>) {
		val adapter = SearchAdapter() { viewModel.getMovieById(it) }
		binding.rvSearchResult.adapter = adapter
		lifecycleScope.launch() {
			adapter.submitData(data)
		}
	}
	
	// если не null, отображаем X при нажатии на который фильтр очистится
	private fun setupFilterButton(filter: Filter?) {
		if (filter == null) {
			binding.btnSearchFilter.apply {
				setOnClickListener {
					val dialog = FilterDialog() {
						viewModel.searchByFilter(it)
					}
					dialog.show(parentFragmentManager, "")
				}
				setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_filter, null))
			}
		} else {
			binding.btnSearchFilter.apply {
				setOnClickListener {
					viewModel.searchByFilter(null)
				}
				setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_clear, null))
			}
		}
	}
	
	private fun showMovieDetails(movie: Movie) {
		findNavController().navigate(R.id.searchDetailFragment, bundleOf(MOVIE_DATA_TAG to movie))
	}
}