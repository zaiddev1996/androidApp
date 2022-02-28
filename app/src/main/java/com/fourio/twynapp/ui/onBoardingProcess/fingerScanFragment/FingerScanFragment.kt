package com.fourio.twynapp.ui.onBoardingProcess.fingerScanFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentFingerScanBinding


class FingerScanFragment : Fragment() {
    private var _binding: FragmentFingerScanBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFingerScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.accountSuccessFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}