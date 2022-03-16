package com.fourio.twynapp.ui.onBoardingProcess.faceScanStartFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.twyn.sdk.listeners.EnrollFaceListener
import br.com.twyn.sdk.ui.FaceActivity
import br.com.twyn.sdk.ui.helpers.EnrollFaceResult
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentFaceScanStartBinding
import com.fourio.twynapp.utils.SharedPref
import android.graphics.Bitmap

import android.util.Base64
import java.io.ByteArrayOutputStream


class FaceScanStartFragment : Fragment(), EnrollFaceListener {
    private var _binding: FragmentFaceScanStartBinding? = null

    private val binding get() = _binding!!
    private var returnedResultsGood: Boolean = false
    private var livenessScore: String = ""
    private var base64Image: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaceScanStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.skipBtn.setOnClickListener {
            SharedPref(requireActivity()).setBooleanValue(
                (getString(R.string.pref_key_face_enrolled)),
                false
            )
            findNavController().navigate(R.id.scanFingerStartFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.includedBtn.cvYellow.setOnClickListener {
            FaceActivity.setEnrollFaceListener(this)
            val myIntent = Intent(activity, FaceActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            startActivity(myIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (returnedResultsGood) {
            val sharedPref = SharedPref(requireActivity())
            sharedPref.setValue(
                requireActivity().getString(R.string.pref_key_face_score),
                livenessScore
            )
            sharedPref.setValue(
                requireActivity().getString(R.string.pref_key_base64_face),
                base64Image
            )
            sharedPref.setBooleanValue((getString(R.string.pref_key_face_enrolled)), true)

            findNavController().navigate(R.id.scanFingerStartFragment)
        }
    }

    override fun onEnrollFaceCompleted(enrollFaceResult: EnrollFaceResult?) {
        if (enrollFaceResult != null) {
            livenessScore = enrollFaceResult.livenessScore.toString()
            base64Image = encodeToBase64(enrollFaceResult.croppedFace)

            Toast.makeText(
                requireContext(),
                "Successful Face Enrollment. Liveness Score ${enrollFaceResult.livenessScore}",
                Toast.LENGTH_LONG
            ).show()
            returnedResultsGood = true
        }
    }

    fun encodeToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


}