package com.fourio.twynapp.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences


class SharedPref(activity: Activity) {
    private var sharedPref: SharedPreferences? = null

    init {
        sharedPref = activity.getSharedPreferences("Enrollment Data", Context.MODE_PRIVATE)

    }

    fun setValue(keyString: String, valueString: String) {
        with(sharedPref!!.edit()) {
            putString(keyString, valueString)
            apply()
        }
    }

    fun setBooleanValue(keyString: String, valueBoolean: Boolean) {
        with(sharedPref!!.edit()) {
            putBoolean(keyString, valueBoolean)
            apply()
        }
    }

    fun getValue(keyString: String): String {
        return sharedPref!!.getString(keyString, "")!!
    }

    fun getBooleanValue(keyString: String): Boolean {
        return sharedPref!!.getBoolean(keyString, false)
    }

}