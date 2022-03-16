package com.fourio.twynapp.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

val Fragment.preference: SharedPreferences get() = requireContext().preference