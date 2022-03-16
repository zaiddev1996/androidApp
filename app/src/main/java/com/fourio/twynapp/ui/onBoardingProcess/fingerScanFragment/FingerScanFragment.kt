package com.fourio.twynapp.ui.onBoardingProcess.fingerScanFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.twyn.sdk.listeners.EnrollFingerListener
import br.com.twyn.sdk.ui.FingerActivity
import br.com.twyn.sdk.ui.helpers.EnrollFingersResult
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentFingerScanBinding
import com.fourio.twynapp.utils.SharedPref


class FingerScanFragment : Fragment(), EnrollFingerListener {
    private var _binding: FragmentFingerScanBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var returnedResultsGood: Boolean = false
    private var leftHandScore: String = ""
    private var rightHandScore: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFingerScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.skipBtn.setOnClickListener {
            if (SharedPref(requireActivity()).getBooleanValue(getString(R.string.pref_key_face_enrolled))) {
                findNavController().navigate(R.id.accountSuccessFragment)
                SharedPref(requireActivity()).setBooleanValue(
                    (getString(R.string.pref_key_finger_enrolled)),
                    false
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "You cannot skip Finger scan if you skipped Face scan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.includedBtn.cvYellow.setOnClickListener {
            FingerActivity.setEnrollFingerListener(this)
            val myIntent = Intent(activity, FingerActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            startActivity(myIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (returnedResultsGood) {
            SharedPref(requireActivity()).setValue(
                requireActivity().getString(R.string.pref_key_left_hand_score),
                leftHandScore
            )
            SharedPref(requireActivity()).setValue(
                requireActivity().getString(R.string.pref_key_right_hand_score),
                rightHandScore
            )
            SharedPref(requireActivity()).setBooleanValue(
                (getString(R.string.pref_key_finger_enrolled)),
                true
            )
            findNavController().navigate(R.id.scanFingerStartFragment)
        }
    }

    override fun onEnrollFingerCompleted(enrollFingersResult: EnrollFingersResult) {
        val keys: Set<*> = enrollFingersResult.fingersMap.keys
        println("keys $keys")
        val i = keys.iterator()
        while (i.hasNext()) {
            val key = i.next() as String
            println("key $key")
            val value = enrollFingersResult.fingersMap[key]
            println("value $value")
        }
        Toast.makeText(
            requireActivity(),
            "Left Hand Score ${enrollFingersResult.leftHandScore} \n Right Hand Score ${enrollFingersResult.rightHandScore}",
            Toast.LENGTH_LONG
        ).show()
        leftHandScore = enrollFingersResult.leftHandScore.toString()
        rightHandScore = enrollFingersResult.rightHandScore.toString()
        returnedResultsGood = true
    }
}