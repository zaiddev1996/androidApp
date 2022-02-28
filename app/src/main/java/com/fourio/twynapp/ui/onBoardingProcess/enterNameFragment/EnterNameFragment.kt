package com.fourio.twynapp.ui.onBoardingProcess.enterNameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.IdentityProvidersAdapter
import com.fourio.twynapp.databinding.FragmentAddIdentitySelectionBinding
import com.fourio.twynapp.databinding.FragmentEnterNameBinding


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
            findNavController().navigate(R.id.enterIdFragment)
        }
        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}