package com.fourio.twynapp.utils.extensions

import android.content.Context
import android.content.SharedPreferences

val Context.preference: SharedPreferences get() = getSharedPreferences("Enrollment Data", Context.MODE_PRIVATE)