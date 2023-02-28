package com.testapp.testkinopoisk.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.testapp.repository.Repository
import com.testapp.repository.RequestResult
import com.testapp.repository.model.Movie
import com.testapp.repository.model.request.Filter
import com.testapp.testkinopoisk.SingleLiveEvent
import com.testapp.testkinopoisk.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : BaseViewModel(), KoinComponent {
	
	private val repository: Repository by inject()
	
	private val _pagingFlow = MutableStateFlow<PagingData<Movie>?>(null)
	val pagingFlow: StateFlow<PagingData<Movie>?> get() = _pagingFlow
	
	private val _movieLiveData = SingleLiveEvent<Movie>()
	val movieLiveData: LiveData<Movie> get() = _movieLiveData
	
	private val _filterLiveData = MutableLiveData<Filter?>(null)
	val filterLiveData: LiveData<Filter?> get() = _filterLiveData
	
	fun searchByName(name: String) {
		viewModelScope.launch {
			repository.searchMoviesByName(name)
				.cachedIn(viewModelScope)
				.collectLatest { _pagingFlow.value = it }
		}
	}
	
	fun searchByFilter(filter: Filter?) {
		_filterLiveData.value = filter
		if (filter == null) return
		viewModelScope.launch {
			repository.searchMoviesWithFilter(filter)
				.cachedIn(viewModelScope)
				.collectLatest { _pagingFlow.value = it }
		}
	}
	
	fun getMovieById(id: Int) {
		viewModelScope.launch {
			when (val result = repository.getMovieById(id)) {
				is RequestResult.Success -> {
					_movieLiveData.postValue(result.data)
				}
				is RequestResult.Error -> {
					_errorLiveData.postValue(result.error)
				}
			}
		}
	}
	
	fun saveMovieToList(movie: Movie) {
		viewModelScope.launch(Dispatchers.IO) {
			repository.saveMovieToList(movie)
		}
	}
	
}