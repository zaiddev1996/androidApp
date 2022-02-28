package com.fourio.twynapp.ui.onBoardingProcess

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.ActivityMainBinding
import com.fourio.twynapp.databinding.ActivityOnBoardingProcessBinding

class OnBoardingProcess : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingProcessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.fragmentContainerView.getFragment().
        setFullscreen()
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