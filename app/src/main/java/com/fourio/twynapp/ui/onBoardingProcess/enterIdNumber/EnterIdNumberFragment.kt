package com.fourio.twynapp.ui.onBoardingProcess.enterIdNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentEnterIdNumberBinding


class EnterIdNumberFragment : Fragment() {
    private var _binding: FragmentEnterIdNumberBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterIdNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.tvBtn.text = getString(R.string.next)


        binding.btnNext.cvYellow.setOnClickListener {
            findNavController().navigate(R.id.enterPhoneFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}