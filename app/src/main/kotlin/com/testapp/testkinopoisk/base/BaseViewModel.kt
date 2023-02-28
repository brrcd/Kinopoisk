package com.testapp.testkinopoisk.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.testapp.repository.model.ApiError
import com.testapp.testkinopoisk.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

	// можно использовать для отображения toast, popup, snackbar с ошибками
	protected val _errorLiveData = SingleLiveEvent<ApiError>()
	val errorLiveData: LiveData<ApiError> get() = _errorLiveData
	
	// можно использовать для отображения загрузки
	protected val _loadingLiveData = SingleLiveEvent<Boolean>()
	val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData
}