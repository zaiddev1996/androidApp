package com.fourio.twynapp.ui.onBoardingProcess.enterPhoneNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.SpinnerAdapter
import com.fourio.twynapp.databinding.FragmentEnterPhoneBinding

class EnterPhoneFragment : Fragment() {
    private var _binding: FragmentEnterPhoneBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.tvBtn.text = getString(R.string.next)


        val modelList: ArrayList<String> = ArrayList()
        modelList.add("+212")
        modelList.add("+243")
        modelList.add("+233")
        modelList.add("+299")
        modelList.add("+204")
        modelList.add("+229")

        val customDropDownAdapter = SpinnerAdapter(requireContext(), modelList)
        binding.phoneSpinner.adapter = customDropDownAdapter

//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.planets_array,
//            R.layout.phone_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            binding.phoneSpinner.adapter = adapter
//        }


        binding.btnNext.cvYellow.setOnClickListener {
            findNavController().navigate(R.id.otpFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}