package com.testapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class GenreEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	@ColumnInfo("name") val name: String?
)

class GenreListConverter {
	
	@TypeConverter
	fun fromGenreList(value: List<GenreEntity>): String {
		val gson = Gson()
		val type = object : TypeToken<List<GenreEntity>>() {}.type
		return gson.toJson(value, type)
	}
	
	@TypeConverter
	fun toGenreList(value: String): List<GenreEntity> {
		val gson = Gson()
		val type = object : TypeToken<List<GenreEntity>>() {}.type
		return gson.fromJson(value, type)
	}
}