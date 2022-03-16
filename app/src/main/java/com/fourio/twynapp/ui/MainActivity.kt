package com.fourio.twynapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.ActivityMainBinding
import com.fourio.twynapp.model.FaceScanInfo
import com.fourio.twynapp.model.FingerScanInfo
import com.fourio.twynapp.repo.repository.Repo
import com.fourio.twynapp.ui.termsAndConditions.TermsAndConditions
import com.fourio.twynapp.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFullscreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.tvCreateAcc.setOnClickListener {
            startActivity(Intent(this, TermsAndConditions::class.java))
        }

        uploadDataIfHave()
    }

    private fun uploadDataIfHave() {
        if (SharedPref(this).getBooleanValue(getString(R.string.pref_key_data_to_upload))) {
            val sharedPref = SharedPref(this)
            val fingerArray = ArrayList<FingerScanInfo>()
            val fingerScanInfo = FingerScanInfo()
            fingerScanInfo.height = 10
            fingerScanInfo.isoquality = 10
            fingerScanInfo.position = 1
            fingerScanInfo.width = 10
            fingerScanInfo.imageData = "Base64"
            fingerScanInfo.internalQuality = 10
            fingerArray.add(fingerScanInfo)
            val faceScanInfo = FaceScanInfo()
            faceScanInfo.faceId = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
            faceScanInfo.height = 10
            faceScanInfo.width = 10
            faceScanInfo.imageData = sharedPref.getValue(getString(R.string.pref_key_base64_face))
            faceScanInfo.internalQuality = 10
            faceScanInfo.isoquality = 10
            faceScanInfo.position = "Frontal"
            val faceArray = ArrayList<FaceScanInfo>()
            faceArray.add(
                faceScanInfo
            )
            GlobalScope.launch {
                withContext(Dispatchers.IO)
                {

                    when (val response = Repo().enrollPerson(
                        sharedPref.getValue(getString(R.string.pref_key_nick)),
                        sharedPref.getValue(getString(R.string.pref_key_phone)),
                        fingerArray,
                        faceArray
                    )) {
                        is Repo.Result.Success -> {
                            withContext(Dispatchers.Main) {
                                sharedPref.setBooleanValue(
                                    getString(R.string.pref_key_data_to_upload),
                                    false
                                )
                            }
                        }
                        is Repo.Result.Failure -> {

                        }
                        is Repo.Result.NetworkError -> {

                        }
                    }
                }

            }
        }
    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT in 21..29) {
            window.statusBarColor = getColor(R.color.dark_app)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= 30) {
            window.statusBarColor = getColor(R.color.dark_app)
            // Making status bar overlaps with the activity
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }

    }
}