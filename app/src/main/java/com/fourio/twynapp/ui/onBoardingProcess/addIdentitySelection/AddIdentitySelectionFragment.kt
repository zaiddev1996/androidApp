package com.fourio.twynapp.ui.onBoardingProcess.addIdentitySelection

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fourio.twynapp.R
import com.fourio.twynapp.adapters.IdentityProvidersAdapter
import com.fourio.twynapp.databinding.FragmentAddIdentitySelectionBinding
import com.fourio.twynapp.model.IdentityProviders
import com.google.gson.Gson
import java.io.*


class AddIdentitySelectionFragment : Fragment() {

    private var _binding: FragmentAddIdentitySelectionBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var identityProvidersAdapter: IdentityProvidersAdapter
    private lateinit var arrayList : List<IdentityProviders.Providers>

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


            identityProvidersAdapter = IdentityProvidersAdapter {
               onItemClick(it)

            }
            adapter = identityProvidersAdapter
            val gridLayoutManager = GridLayoutManager(activity, 3)
            layoutManager = gridLayoutManager


        }

        readVerificationsJson()

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
        arrayList.get(position).selected = !arrayList[position].selected
        identityProvidersAdapter.submitList(arrayList)
        identityProvidersAdapter.notifyDataSetChanged()
    }


    fun readVerificationsJson(){
        val istream: InputStream = resources.openRawResource(R.raw.identity_providers)
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
        val identityFeed: IdentityProviders = gson.fromJson(jsonString, IdentityProviders::class.java)
        Log.e("JSON_FILE", jsonString)
        arrayList =  identityFeed.identity_providers
        identityProvidersAdapter.submitList(arrayList)
    }

}