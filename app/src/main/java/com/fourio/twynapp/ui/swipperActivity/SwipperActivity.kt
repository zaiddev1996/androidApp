package com.fourio.twynapp.ui.swipperActivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.ActivitySwipperBinding
import com.fourio.twynapp.ui.dashboard.DashboardActivity

class SwipperActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwipperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwipperBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFullscreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.cvSkip.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT in 21..29) {
            window.statusBarColor = getColor(R.color.light_blue)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= 30) {
            window.statusBarColor = getColor(R.color.light_blue)
            // Making status bar overlaps with the activity
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }

    }
}