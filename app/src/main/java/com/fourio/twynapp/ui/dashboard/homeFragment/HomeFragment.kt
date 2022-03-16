package com.fourio.twynapp.ui.dashboard.homeFragment

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fourio.twynapp.adapters.FeedAdapter
import com.fourio.twynapp.ui.customGuageView.CustomGuageView
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FragmentHomeBinding
import com.fourio.twynapp.model.IdentityFeed
import com.fourio.twynapp.utils.SharedPref
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

        setRecyclerView()


        setValuesForLeftHandGuageAnimation()
        setValuesForRightHandGuageAnimation()
        setValuesForFaceGuageAnimation()


    }

    fun setValuesForRightHandGuageAnimation() {
        binding.guageViewDoc.ivGraphType.setImageDrawable(
            ResourcesCompat.getDrawable(
                requireActivity().resources,
                R.drawable.ic_finger_print_login,
                null
            )
        )
        binding.guageViewDoc.tvGraphType.text = "Right Hand Scan"
        if (SharedPref(requireActivity()).getBooleanValue(getString(R.string.pref_key_finger_enrolled))) {
            val leftHandScore = SharedPref(requireActivity()).getValue(
                getString(
                    R.string.pref_key_left_hand_score
                )
            ).toFloat()
            animateGuage(
                binding.guageViewDoc.guageView,
                binding.guageViewDoc.viewNeedle,
                leftHandScore * 180
            )
            if (leftHandScore >= 0.9) {
                binding.guageViewDoc.rbGraphScore.rating = 5f
            } else {
                binding.guageViewDoc.rbGraphScore.rating = leftHandScore / 2
            }
        }
    }

    fun setValuesForLeftHandGuageAnimation() {
        binding.guageViewFinger.ivGraphType.setImageDrawable(
            ResourcesCompat.getDrawable(
                requireActivity().resources,
                R.drawable.ic_finger_print_login,
                null
            )
        )
        binding.guageViewFinger.tvGraphType.text = "Left Hand Scan"
        if (SharedPref(requireActivity()).getBooleanValue(getString(R.string.pref_key_finger_enrolled))) {
            val leftHandScore = SharedPref(requireActivity()).getValue(
                getString(
                    R.string.pref_key_left_hand_score
                )
            ).toFloat()
            animateGuage(
                binding.guageViewFinger.guageView,
                binding.guageViewFinger.viewNeedle,
                leftHandScore * 180
            )
            if (leftHandScore >= 0.9) {
                binding.guageViewFinger.rbGraphScore.rating = 5f
            } else {
                binding.guageViewFinger.rbGraphScore.rating = leftHandScore / 2
            }
        }
    }

    fun setValuesForFaceGuageAnimation() {
        if (SharedPref(requireActivity()).getBooleanValue(getString(R.string.pref_key_face_enrolled))) {
            val faceScore = SharedPref(requireActivity()).getValue(
                getString(
                    R.string.pref_key_face_score
                )
            ).toFloat()
            animateGuage(
                binding.guageViewFace.guageView,
                binding.guageViewFace.viewNeedle,
                faceScore * 180
            )
            if (faceScore >= 0.9) {
                binding.guageViewFace.rbGraphScore.rating = 5f
            } else {
                binding.guageViewFace.rbGraphScore.rating = faceScore / 2
            }
        }
    }


    fun setRecyclerView() {
        binding.tvNick.text =
            ("I am ${SharedPref(requireActivity()).getValue(getString(com.fourio.twynapp.R.string.pref_key_nick))}")
        binding.rvFeed.apply {
            val feedAdapter = FeedAdapter(requireContext()) {
//                onItemClick(it)
            }
            adapter = feedAdapter
            val gridLayoutManager = LinearLayoutManager(activity)
            layoutManager = gridLayoutManager
            feedAdapter.submitList(readVerificationsJson())
        }
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

    fun readVerificationsJson(): List<IdentityFeed.Requests> {
        val istream: InputStream =
            resources.openRawResource(com.fourio.twynapp.R.raw.dashboard_identity_feed)
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
//        Log.e("JSON_FILE", jsonString)
        return identityFeed.identity_feed
    }
}