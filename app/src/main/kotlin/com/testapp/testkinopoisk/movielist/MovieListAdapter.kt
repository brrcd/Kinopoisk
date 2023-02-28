package com.testapp.testkinopoisk.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.testapp.repository.model.Movie
import com.testapp.testkinopoisk.R
import com.testapp.testkinopoisk.databinding.ItemMovieBinding

class MovieListAdapter(private val onMovieSelected: (Movie) -> Unit = { }) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
	
	private var list = ArrayList<Movie>()
	
	fun updateList(newList: List<Movie>) {
		val diffCallback = MovieListDiffUtil(list, newList)
		val diffResult = DiffUtil.calculateDiff(diffCallback)
		list.clear()
		list.addAll(newList)
		diffResult.dispatchUpdatesTo(this)
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
		val binding =
			ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MovieViewHolder(binding)
	}
	
	override fun getItemCount() = list.size
	
	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
		holder.bindData(list[position])
	
	inner class MovieViewHolder(private val binding: ItemMovieBinding) :
		RecyclerView.ViewHolder(binding.root) {
		
		fun bindData(movie: Movie) {
			binding.root.setOnClickListener {
				onMovieSelected.invoke(movie)
			}
			
			binding.txtViewTitle.text = movie.name
			binding.txtViewRating.text = "${movie.rating.kp}"
			binding.imgPosterPreview.load(movie.poster.previewUrl.ifEmpty {
				movie.poster.url
			}) {
				error(R.drawable.ic_placeholder)
			}
		}
	}
}