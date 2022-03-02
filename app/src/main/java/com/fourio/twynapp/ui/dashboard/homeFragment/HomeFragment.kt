package com.fourio.twynapp.ui.dashboard.homeFragment

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fourio.twynapp.adapters.FeedAdapter
import com.fourio.twynapp.databinding.FragmentHomeBinding
import com.fourio.twynapp.ui.customGuageView.CustomGuageView
import android.R
import android.util.Log
import com.fourio.twynapp.model.IdentityFeed
import com.google.gson.Gson
import java.io.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var arrayList: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFeed.apply {
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

            val feedAdapter = FeedAdapter(requireContext()) {
//                onItemClick(it)

            }
            adapter = feedAdapter
            val gridLayoutManager = LinearLayoutManager(activity)
            layoutManager = gridLayoutManager

            feedAdapter.submitList(readVerificationsJson())
        }
        animateGuage(binding.guageViewDoc.guageView, binding.guageViewDoc.viewNeedle, 115f)
        animateGuage(binding.guageViewFace.guageView, binding.guageViewFace.viewNeedle, 45f)
        animateGuage(binding.guageViewFinger.guageView, binding.guageViewFinger.viewNeedle, 160f)


    }

    fun animateGuage(guageView: CustomGuageView, needleView: View, angle: Float) {
        val va = ValueAnimator.ofFloat(0f, angle)
        va.duration = 1000 //in millis
        va.addUpdateListener { animation ->
            guageView.setAngle(animation.animatedValue as Float)
            needleView.rotation = (-90 + animation.animatedValue as Float)
        }
        va.repeatCount = 0
        va.start()
    }

    fun readVerificationsJson(): List<IdentityFeed.Requests>{
        val istream: InputStream = resources.openRawResource(com.fourio.twynapp.R.raw.dashboard_identity_feed)
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
        val identityFeed: IdentityFeed = gson.fromJson(jsonString, IdentityFeed::class.java)
        Log.e("JSON_FILE", jsonString)
        return identityFeed.identity_feed
    }
}