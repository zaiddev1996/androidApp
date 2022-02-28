package com.fourio.twynapp.ui.onBoardingProcess.accountSuccessFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fourio.twynapp.databinding.FragmentAccountSuccessBinding
import com.fourio.twynapp.ui.swipperActivity.SwipperActivity

class AccountSuccessFragment : Fragment() {

    private var _binding: FragmentAccountSuccessBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.includedBtn.cvYellow.setOnClickListener {
           startActivity(Intent(requireContext(), SwipperActivity::class.java))
        }
    }

}