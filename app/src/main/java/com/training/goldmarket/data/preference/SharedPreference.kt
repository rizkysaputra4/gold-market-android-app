package com.training.goldmarket.data.preference

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreference @Inject constructor(val sharedPref: SharedPreferences) {

    fun save(KEY_NAME: String, text: String) {
        val saveData: SharedPreferences.Editor = sharedPref.edit()
        saveData.putString(KEY_NAME, text).apply()
    }

    fun retrieve(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun clearData(KEY_NAME: String) {
        sharedPref.edit().clear().remove(KEY_NAME).apply()
    }
}