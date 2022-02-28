package com.fourio.twynapp.ui.dashboard.historyFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.FeedAdapter
import com.fourio.twynapp.adapters.HistoryAdapter
import com.fourio.twynapp.databinding.FragmentHistoryBinding
import com.fourio.twynapp.databinding.FragmentHomeBinding

class historyFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var arrayList : ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHistory.apply {
            arrayList = ArrayList()
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")
            arrayList.add("test")

            val feedAdapter = HistoryAdapter {
//                onItemClick(it)

            }
            adapter = feedAdapter
            val gridLayoutManager = LinearLayoutManager(activity)
            layoutManager = gridLayoutManager

            feedAdapter.submitList(arrayList)
        }
    }
}