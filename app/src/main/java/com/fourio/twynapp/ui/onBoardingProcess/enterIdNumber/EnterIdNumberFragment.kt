package com.fourio.twynapp.ui.onBoardingProcess.enterIdNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentEnterIdNumberBinding
import com.fourio.twynapp.utils.SharedPref


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
            if (binding.etId.text.isNotEmpty()) {
                SharedPref(requireActivity()).setValue(
                    requireActivity().getString(R.string.pref_key_id),
                    binding.etId.text.toString()
                )
                findNavController().navigate(R.id.enterPhoneFragment)
            } else {
                Toast.makeText(requireContext(), "Please enter id first.", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}