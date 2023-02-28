package com.testapp.testkinopoisk.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testapp.repository.Repository
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.SingleLiveEvent
import com.testapp.testkinopoisk.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieListViewModel : BaseViewModel(), KoinComponent {
	
	private val repository: Repository by inject()
	
	private val _movieListData = MutableLiveData<List<Movie>>()
	val movieListData: LiveData<List<Movie>> get() = _movieListData
	
	private val _onMovieRemoved = SingleLiveEvent<Boolean>()
	val onMovieRemoved: LiveData<Boolean> get() = _onMovieRemoved
	
	fun removeMovieFromListById(movieId: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			_onMovieRemoved.postValue(true)
			repository.removeMovieFromListById(movieId)
		}
	}
	
	fun getMovieList() {
		viewModelScope.launch {
			repository.getSavedMovies().collect() {
				_movieListData.postValue(it)
			}
		}
	}
}