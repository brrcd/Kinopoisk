package com.testapp.testkinopoisk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<T : ViewBinding>(
	private val inflate: Inflate<T>
): BottomSheetDialogFragment() {
	
	private var _binding: T? = null
	val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = inflate.invoke(inflater, container, false)
		return binding.root
	}
	
	// bottom sheet будет развёрнут на свою максимальную высоту
	// при сворачивании пропускаем полусвернутный
	override fun onStart() {
		super.onStart()
		val behavior = (this.dialog as BottomSheetDialog).behavior
		behavior.state = BottomSheetBehavior.STATE_EXPANDED
		behavior.skipCollapsed = true
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}