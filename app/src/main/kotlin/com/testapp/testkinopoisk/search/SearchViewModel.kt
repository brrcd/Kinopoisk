package com.testapp.testkinopoisk.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.testapp.data.model.Filter
import com.testapp.data.model.Movie
import com.testapp.data.model.entity.toMovieEntity
import com.testapp.data.model.toMovie
import com.testapp.data.source.Repository
import com.testapp.data.source.remote.RequestResult
import com.testapp.testkinopoisk.SingleLiveEvent
import com.testapp.testkinopoisk.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
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
				.map { data ->
					data.map { it.toMovie() }
				}
				.cachedIn(viewModelScope)
				.collectLatest { _pagingFlow.value = it }
		}
	}
	
	fun searchByFilter(filter: Filter?) {
		_filterLiveData.value = filter
		if (filter == null) return
		viewModelScope.launch {
			repository.searchMoviesWithFilter(filter)
				.map { data ->
					data.map { it.toMovie() }
				}
				.cachedIn(viewModelScope)
				.collectLatest { _pagingFlow.value = it }
		}
	}
	
	fun getMovieById(id: Int) {
		viewModelScope.launch {
			when (val result = repository.getMovieById(id)) {
				is RequestResult.Success -> {
					_movieLiveData.postValue(result.data.toMovie())
				}
				is RequestResult.Error -> {
					_errorLiveData.postValue(result.error)
				}
			}
		}
	}
	
	fun saveMovieToList(movie: Movie) {
		viewModelScope.launch(Dispatchers.IO) {
			repository.saveMovieToList(movie.toMovieEntity())
		}
	}
	
}