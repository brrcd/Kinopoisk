package com.testapp.testkinopoisk.movielist

import androidx.recyclerview.widget.DiffUtil
import com.testapp.repository.model.Movie

// диффутил колбэк для списка фильмов из БД
class MovieListDiffUtil(
	private val oldList: List<Movie>,
	private val newList: List<Movie>
): DiffUtil.Callback() {
	
	override fun getOldListSize() = oldList.size
	
	override fun getNewListSize() = newList.size
	
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition].id == newList[newItemPosition].id
	}
	
	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition] == newList[newItemPosition]
	}
	
	// для использования анимации, изменения отдельных полей view holder'а
	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
		return super.getChangePayload(oldItemPosition, newItemPosition)
	}
}