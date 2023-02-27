package com.testapp.testkinopoisk.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.res.ResourcesCompat
import com.testapp.testkinopoisk.R

// кастомная вью для отображения трёх состояний
class SortByButton @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null
) : AppCompatImageButton(context, attrs) {
	
	private var sortState: SortByState = SortByState.NO_SORT
	
	init {
		this.setOnClickListener {
			val drawable = when (sortState) {
				SortByState.NO_SORT -> {
					sortState = SortByState.DESCENDING
					ResourcesCompat.getDrawable(resources, R.drawable.ic_down, null)
				}
				SortByState.ASCENDING -> {
					sortState = SortByState.NO_SORT
					ResourcesCompat.getDrawable(resources, R.drawable.ic_clear, null)
				}
				SortByState.DESCENDING -> {
					sortState = SortByState.ASCENDING
					ResourcesCompat.getDrawable(resources, R.drawable.ic_up, null)
				}
			}
			(it as AppCompatImageButton).setImageDrawable(drawable)
		}
	}
	
	private enum class SortByState {
		NO_SORT, ASCENDING, DESCENDING
	}
	
	fun getSortType() = when (sortState) {
		SortByState.NO_SORT -> 0
		SortByState.ASCENDING -> 1
		SortByState.DESCENDING -> -1
	}
}