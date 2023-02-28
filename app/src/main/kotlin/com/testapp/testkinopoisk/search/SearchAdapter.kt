package com.testapp.testkinopoisk.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.databinding.ItemMovieSearchBinding

class SearchAdapter(private val onMovieSelected: (Int) -> Unit = { }) :
	PagingDataAdapter<Movie, SearchAdapter.SearchViewHolder>(MovieItemDiffUtilCallback()) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
		val binding =
			ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return SearchViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
		getItem(position)?.let {
			holder.bindData(it)
		}
	}
	
	inner class SearchViewHolder(private val binding: ItemMovieSearchBinding) :
		ViewHolder(binding.root) {
		
		fun bindData(movie: Movie) {
			
			binding.txtViewTitle.text = movie.name
			binding.txtViewRating.text = "${movie.rating.kp}"
			binding.imgPosterPreview.load(movie.poster.previewUrl.ifEmpty {
				movie.poster.url
			}) {
				error(R.drawable.ic_placeholder)
			}
			
			binding.root.setOnClickListener { onMovieSelected.invoke(movie.id) }
		}
	}
	
	companion object{
		private class MovieItemDiffUtilCallback : DiffUtil.ItemCallback<Movie>(){
			override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
				return oldItem.id == newItem.id
			}
			
			override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
				return oldItem == newItem
			}
		}
	}
}