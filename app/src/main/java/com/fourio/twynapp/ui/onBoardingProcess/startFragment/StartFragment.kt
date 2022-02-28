package com.fourio.twynapp.ui.onBoardingProcess.startFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLetsStart.cvYellow.setOnClickListener {
            findNavController().navigate(R.id.addIdentityFagment)
        }
        binding.tvBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.backArrow.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}