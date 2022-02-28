package com.fourio.twynapp.ui.termsAndConditions

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.fourio.twynapp.R
import android.text.method.ScrollingMovementMethod
import com.fourio.twynapp.databinding.ActivityTermsAndConditionsBinding
import com.fourio.twynapp.ui.onBoardingProcess.OnBoardingProcess


class TermsAndConditions : AppCompatActivity() {
    private lateinit var binding: ActivityTermsAndConditionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate((layoutInflater))
        setContentView(binding.root)

        setFullscreen()
        binding.tvTermsAndConditions.movementMethod = ScrollingMovementMethod()
        binding.btnAgree.setOnClickListener {
            startActivity(Intent(this, OnBoardingProcess::class.java))
        }
    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT in 21..29) {
            window.statusBarColor = getColor(R.color.dark_blue)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= 30) {
            window.statusBarColor = getColor(R.color.dark_blue)
            // Making status bar overlaps with the activity
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }

    }
}