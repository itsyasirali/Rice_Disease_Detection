package com.itsyasirali.ricediseasedetection.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.itsyasirali.ricediseasedetection.models.UserModel

class SharedPrefManager(context: Context) {

    companion object {
        private const val PREF_NAME = "user_pref"
        private const val KEY_USER = "key_user"
        private const val KEY_IS_LOGGED_IN = "key_is_logged_in"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveUser(user: UserModel) {
        val json = gson.toJson(user)
        prefs.edit().putString(KEY_USER, json).apply()
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }

    fun getUser(): UserModel? {
        val json = prefs.getString(KEY_USER, null)
        return if (!json.isNullOrEmpty()) gson.fromJson(json, UserModel::class.java) else null
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}
