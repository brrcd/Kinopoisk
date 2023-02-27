package com.testapp.data.source.local

import android.content.Context

// здесь мы можем хранить наши постоянные данные базовых типов
// для более безопасного хранения можно использовать EncryptedSharedPreferences, KeyChain
class SharedPrefs(context: Context) {
	
	private val prefs = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
	private val editor = prefs.edit()
	
	var userName: String
		get() = prefs.getString(USER_NAME, "") ?: ""
		set(value) = editor.putString(USER_NAME, value).apply()
	
	companion object {
		private const val USER_NAME = "user_name"
	}
}