package com.fourio.twynapp.ui.dashboard.settingsFragment

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.FeedAdapter
import com.fourio.twynapp.databinding.FragmentHomeBinding
import com.fourio.twynapp.databinding.FragmentSettingsBinding
import com.fourio.twynapp.ui.customGuageView.CustomGuageView

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewSupport.setOnClickListener {
            findNavController().navigate(R.id.faqFragment)
        }

    }


}