package com.testapp.testkinopoisk.search.filter

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.CheckBox
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.testapp.repository.model.request.Filter
import com.testapp.repository.model.request.SearchField
import com.testapp.repository.model.request.SortField
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.custom.SortByButton
import kotlin.properties.Delegates

class FilterDialog(private val onDismissListener: (Filter?) -> Unit = {}) : DialogFragment() {
	
	private var filterByRating: CheckBox by Delegates.notNull()
	private var filterByYear: CheckBox by Delegates.notNull()
	
	private var ratingFrom: NumberPicker by Delegates.notNull()
	private var ratingTo: NumberPicker by Delegates.notNull()
	private var yearFrom: NumberPicker by Delegates.notNull()
	private var yearTo: NumberPicker by Delegates.notNull()
	
	private var sortByRating: SortByButton by Delegates.notNull()
	private var sortByYear: SortByButton by Delegates.notNull()
	
	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		return activity?.let {
			val builder = AlertDialog.Builder(it)
			val view = layoutInflater.inflate(R.layout.dialog_filter, null)
			builder.setView(view)
			
			ratingFrom = view.findViewById<NumberPicker>(R.id.nmbPckRatingFrom).apply {
				maxValue = 10
				minValue = 0
			}
			ratingTo = view.findViewById<NumberPicker>(R.id.nmbPckRatingTo).apply {
				maxValue = 10
				minValue = 1
			}
			yearFrom = view.findViewById<NumberPicker>(R.id.nmbPckYearFrom).apply {
				maxValue = 2023
				minValue = 1900
			}
			yearTo = view.findViewById<NumberPicker>(R.id.nmbPckYearTo).apply {
				maxValue = 2023
				minValue = 1900
			}
			filterByRating = view.findViewById(R.id.chkBoxByRating)
			filterByYear = view.findViewById(R.id.chkBoxByYear)
			sortByRating = view.findViewById(R.id.btnSortByRating)
			sortByYear = view.findViewById(R.id.btnSortByYear)
			
			val dialog = builder.create()
			dialog
		} ?: throw IllegalStateException("Activity cannot be null")
	}
	
	// слушатель будет срабатывать только тапу на пустое место
	override fun onDismiss(dialog: DialogInterface) {
		super.onDismiss(dialog)
		val searchFields = mutableListOf<SearchField>()
		if (filterByRating.isChecked) {
			searchFields.add(SearchField("rating.kp", "${ratingFrom.value}-${ratingTo.value}"))
		}
		if (filterByYear.isChecked) {
			searchFields.add(SearchField("year", "${yearFrom.value}-${yearTo.value}"))
		}
		val sortField = mutableListOf<SortField>()
		if (sortByRating.getSortType() != 0) {
			sortField.add(SortField("rating.kp", "${sortByRating.getSortType()}"))
		}
		if (sortByYear.getSortType() != 0) {
			sortField.add(SortField("year", "${sortByYear.getSortType()}"))
		}
		val filter = if (searchFields.isNotEmpty() && sortField.isNotEmpty()) Filter(search = searchFields, sort = sortField)
		else null
		onDismissListener.invoke(filter)
	}
}