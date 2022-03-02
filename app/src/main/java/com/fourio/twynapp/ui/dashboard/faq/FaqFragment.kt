package com.fourio.twynapp.ui.dashboard.faq

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.FaqAdapter
import com.fourio.twynapp.adapters.IdentityProvidersAdapter
import com.fourio.twynapp.databinding.FragmentAddIdentitySelectionBinding
import com.fourio.twynapp.databinding.FragmentFaqBinding


class FaqFragment : Fragment() {
    private var _binding: FragmentFaqBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var identityProvidersAdapter: FaqAdapter
    private lateinit var arrayList: ArrayList<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFaq.apply {
            arrayList = ArrayList()
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(false)
            identityProvidersAdapter = FaqAdapter(requireContext()) {
                onItemClick(it)

            }
            adapter = identityProvidersAdapter
            val gridLayoutManager = LinearLayoutManager(activity)
            layoutManager = gridLayoutManager

            identityProvidersAdapter.submitList(arrayList)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemClick(position: Int) {
        arrayList[position] = !arrayList[position]
        identityProvidersAdapter.submitList(arrayList)
        identityProvidersAdapter.notifyDataSetChanged()
    }
}