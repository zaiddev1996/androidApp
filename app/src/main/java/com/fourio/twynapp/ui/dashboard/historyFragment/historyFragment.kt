package com.fourio.twynapp.ui.dashboard.historyFragment

import android.os.Bundle
import android.util.Log
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
import com.fourio.twynapp.model.IdentityFeed
import com.fourio.twynapp.model.IdentityHistory
import com.google.gson.Gson
import java.io.*

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

            val feedAdapter = HistoryAdapter(requireContext()) {
//                onItemClick(it)

            }
            adapter = feedAdapter
            val gridLayoutManager = LinearLayoutManager(activity)
            layoutManager = gridLayoutManager

            feedAdapter.submitList(readVerificationsJson())
        }
    }

    fun readVerificationsJson(): List<IdentityFeed.Requests>{
        val istream: InputStream = resources.openRawResource(com.fourio.twynapp.R.raw.identity_history)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(istream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } finally {
            istream.close()
        }
        val jsonString: String = writer.toString()
        val gson = Gson()
        val identityFeed: IdentityHistory = gson.fromJson(jsonString, IdentityHistory::class.java)
        Log.e("JSON_FILE", jsonString)
        return identityFeed.identity_history
    }
}