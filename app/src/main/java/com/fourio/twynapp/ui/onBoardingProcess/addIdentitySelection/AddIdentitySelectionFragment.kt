package com.fourio.twynapp.ui.onBoardingProcess.addIdentitySelection

import android.annotation.SuppressLint
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


class AddIdentitySelectionFragment : Fragment() {

    private var _binding: FragmentAddIdentitySelectionBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var identityProvidersAdapter: IdentityProvidersAdapter
    private lateinit var arrayList : ArrayList<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddIdentitySelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvIdentityProviders.apply {
             arrayList = ArrayList()
            arrayList.add(true)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(true)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            arrayList.add(true)
            arrayList.add(false)
            identityProvidersAdapter = IdentityProvidersAdapter {
               onItemClick(it)

            }
            adapter = identityProvidersAdapter
            val gridLayoutManager = GridLayoutManager(activity, 3)
            layoutManager = gridLayoutManager

            identityProvidersAdapter.submitList(arrayList)
        }

        binding.includedBtn.cvYellow.setOnClickListener {
            findNavController().navigate(R.id.enterNameFragment)
        }

        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.backArrow.setOnClickListener {
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