package com.fourio.twynapp.utils.extensions

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import com.fourio.twynapp.BuildConfig
import com.google.gson.Gson

@SuppressLint("ApplySharedPref")
fun SharedPreferences.putAny(
    key: String,
    value: Any,
    backgroundThread: Boolean = false
) {
    with(edit()) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            else -> {
                val json = Gson().toJson(value)
                putString(key, json)
            }
        }
        if (backgroundThread) {
            apply()
        } else {
            commit()
        }
    }
}

fun Any.logD(message: String){
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}