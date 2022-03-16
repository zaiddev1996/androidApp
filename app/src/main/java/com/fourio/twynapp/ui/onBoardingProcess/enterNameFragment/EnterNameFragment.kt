package com.fourio.twynapp.ui.onBoardingProcess.enterNameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentEnterNameBinding
import com.fourio.twynapp.utils.SharedPref


class EnterNameFragment : Fragment() {


    private var _binding: FragmentEnterNameBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.tvBtn.text = getString(R.string.next)


        binding.btnNext.cvYellow.setOnClickListener {
            if (binding.etNick.text.isNotEmpty()) {
                SharedPref(requireActivity()).setValue(
                    requireActivity().getString(R.string.pref_key_nick),
                    binding.etNick.text.toString()
                )
                findNavController().navigate(R.id.enterIdFragment)
            } else {
                Toast.makeText(requireContext(), "Please enter name first.", Toast.LENGTH_SHORT)
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