package com.fourio.twynapp.ui.onBoardingProcess.accountSuccessFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentAccountSuccessBinding
import com.fourio.twynapp.model.FaceScanInfo
import com.fourio.twynapp.model.FingerScanInfo
import com.fourio.twynapp.repo.repository.Repo
import com.fourio.twynapp.ui.swipperActivity.SwipperActivity
import com.fourio.twynapp.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountSuccessFragment : Fragment() {

    private var _binding: FragmentAccountSuccessBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.includedBtn.cvYellow.setOnClickListener {
//           startActivity(Intent(requireContext(), SwipperActivity::class.java))
            binding.includedBtn.tvBtn.visibility = View.GONE
            binding.includedBtn.progressBtn.visibility = View.VISIBLE
            binding.includedBtn.cvYellow.isClickable = false
            val sharedPref = SharedPref(requireActivity())

            val fingerArray = ArrayList<FingerScanInfo>()
            val fingerScanInfo = FingerScanInfo()
            if (sharedPref.getBooleanValue(getString(R.string.pref_key_finger_enrolled))) {
                fingerScanInfo.height = 10
                fingerScanInfo.isoquality = 10
                fingerScanInfo.position = 1
                fingerScanInfo.width = 10
                fingerScanInfo.imageData = "Base64"
                fingerScanInfo.internalQuality = 10
                fingerArray.add(fingerScanInfo)
            }

            val faceArray = ArrayList<FaceScanInfo>()
            val faceScanInfo = FaceScanInfo()
            if (sharedPref.getBooleanValue(getString(R.string.pref_key_face_enrolled))) {
                faceScanInfo.faceId = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                faceScanInfo.height = 10
                faceScanInfo.width = 10
                faceScanInfo.imageData =
                    sharedPref.getValue(getString(R.string.pref_key_base64_face))
                faceScanInfo.internalQuality = 10
                faceScanInfo.isoquality = 10
                faceScanInfo.position = "Frontal"
                faceArray.add(
                    faceScanInfo
                )
            }

            viewLifecycleOwner.lifecycleScope.launch {
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
                                binding.includedBtn.tvBtn.visibility = View.VISIBLE
                                binding.includedBtn.progressBtn.visibility = View.GONE
                                binding.includedBtn.cvYellow.isClickable = true
                                startActivity(
                                    Intent(
                                        requireActivity(),
                                        SwipperActivity::class.java
                                    )
                                )
                            }
                        }
                        is Repo.Result.Failure -> {
                            withContext(Dispatchers.Main) {
                                binding.includedBtn.tvBtn.visibility = View.VISIBLE
                                binding.includedBtn.progressBtn.visibility = View.GONE
                                binding.includedBtn.cvYellow.isClickable = true
                                Toast.makeText(
                                    requireContext(),
                                    "Some error happened",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                        is Repo.Result.NetworkError -> {
                            withContext(Dispatchers.Main) {
                                sharedPref.setBooleanValue(
                                    getString(R.string.pref_key_data_to_upload),
                                    true
                                )
                                startActivity(
                                    Intent(
                                        requireActivity(),
                                        SwipperActivity::class.java
                                    )
                                )
                                binding.includedBtn.tvBtn.visibility = View.VISIBLE
                                binding.includedBtn.progressBtn.visibility = View.GONE
                                binding.includedBtn.cvYellow.isClickable = true
                                Toast.makeText(
                                    requireContext(),
                                    "No Internet Connection... App will retry once internet is active.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    }
                }

            }


        }
    }

}