package com.fourio.twynapp.ui.dashboard

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.ActivityDashboardBinding
import com.fourio.twynapp.utils.extensions.preference
import com.google.android.material.bottomsheet.BottomSheetDialog

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullscreen()
        setListeners()

        Handler().postDelayed({
            showBottomSheetRequest()
        }, 5000)

        Handler().postDelayed({
            showBottomSheetReview()
        }, 10000)


        window.navigationBarColor = (resources.getColor(R.color.navigation_bar_color));
    }

    private fun setListeners() {
        binding.cvHomeMenu.setOnClickListener {
            onHomeClicked()
        }

        binding.cvServicesMenu.setOnClickListener {
            onServicesClicked()
        }

        binding.cvHistoryMenu.setOnClickListener {
            onHistoryClicked()
        }

        binding.cvSettingsMenu.setOnClickListener {
            onSettingsClicked()
        }
    }

    private fun showBottomSheetRequest() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.TransparentDialog)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_request)
        bottomSheetDialog.window!!.setDimAmount(0.5F)
        val closeBtn = bottomSheetDialog.findViewById<View>(R.id.iv_close)
        closeBtn!!.setOnClickListener {
            bottomSheetDialog.dismissWithAnimation = true
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun showBottomSheetReview() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.TransparentDialog)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_review)
        bottomSheetDialog.window!!.setDimAmount(0.5F)
        val closeBtn = bottomSheetDialog.findViewById<View>(R.id.iv_close)
        closeBtn!!.setOnClickListener {
            bottomSheetDialog.dismissWithAnimation = true
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun onHomeClicked() {
        hideAllDots()
        binding.viewDotHome.visibility = View.VISIBLE
        binding.fragmentContainerView.getFragment<Fragment>().findNavController()
            .navigate(R.id.homeFragment)
    }

    private fun onServicesClicked() {
        hideAllDots()
        binding.viewDotServices.visibility = View.VISIBLE
        binding.fragmentContainerView.getFragment<Fragment>().findNavController()
            .navigate(R.id.servicesFragment)
    }

    private fun onHistoryClicked() {
        hideAllDots()
        binding.viewDotHistory.visibility = View.VISIBLE
        binding.fragmentContainerView.getFragment<Fragment>().findNavController()
            .navigate(R.id.historyFragment)
    }

    private fun onSettingsClicked() {
        hideAllDots()
        binding.viewDotSettings.visibility = View.VISIBLE
        binding.fragmentContainerView.getFragment<Fragment>().findNavController()
            .navigate(R.id.settingsFragment)
    }

    private fun hideAllDots() {
        binding.viewDotHome.visibility = View.GONE
        binding.viewDotHistory.visibility = View.GONE
        binding.viewDotServices.visibility = View.GONE
        binding.viewDotSettings.visibility = View.GONE
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