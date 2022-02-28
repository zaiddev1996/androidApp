package com.fourio.twynapp.ui.onBoardingProcess.faceScanStartFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentFaceScanStartBinding


class FaceScanStartFragment : Fragment() {
    private var _binding: FragmentFaceScanStartBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaceScanStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.scanFingerStartFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}