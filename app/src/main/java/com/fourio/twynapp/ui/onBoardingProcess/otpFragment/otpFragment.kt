package com.fourio.twynapp.ui.onBoardingProcess.otpFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentOtpBinding


class otpFragment : Fragment() {
    private var _binding: FragmentOtpBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includedBtn.tvBtn.text = getString(R.string.submit)



        binding.includedBtn.cvYellow.setOnClickListener {
            findNavController().navigate(R.id.scanFaceStartFragment)
        }
        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}