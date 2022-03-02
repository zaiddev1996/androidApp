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
import com.fourio.twynapp.adapters.FeedAdapter
import com.fourio.twynapp.adapters.SwipeCardAdapter
import com.fourio.twynapp.databinding.ActivitySwipperBinding
import com.fourio.twynapp.ui.dashboard.DashboardActivity
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

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

        val  arrayList = ArrayList<String>()
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")
        arrayList.add("test")

       binding.cvBankDetails
        val cardStackLayoutManager = CardStackLayoutManager(this)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackLayoutManager.setSwipeThreshold(0.1f)
        binding.cvBankDetails.layoutManager = cardStackLayoutManager

        val feedAdapter= SwipeCardAdapter(){

        }
        binding.cvBankDetails.adapter = feedAdapter
        feedAdapter.submitList(arrayList)

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