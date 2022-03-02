package com.fourio.twynapp.adapters

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.databinding.IdentityProviderItemBinding
import com.fourio.twynapp.model.IdentityProviders


class IdentityProvidersAdapter(private val callback: (Int) -> Unit) : ListAdapter<IdentityProviders.Providers, IdentityProvidersAdapter.ViewHolder>(DiffCallback()){


    class ViewHolder(private val binding: IdentityProviderItemBinding, private val callback: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IdentityProviders.Providers){
            binding.apply {
                if (item.selected){
                    binding.cvTick.visibility = View.VISIBLE
                }else {
                    binding.cvTick.visibility = View.GONE
                }

                binding.root.setOnClickListener {
                    if (item.selected){
                        binding.cvTick.visibility = View.GONE
                    }else {
                        binding.cvTick.visibility = View.VISIBLE
                    }
                    callback(layoutPosition)
                }
                val decodedString: ByteArray =
                    Base64.decode(item.logo, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                ivProvider.setImageBitmap(decodedByte)
                tvName.text = item.name


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = IdentityProviderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, callback)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLeague = getItem(position)
        holder.bind(currentLeague)
        holder.setIsRecyclable(true)
    }

    class  DiffCallback: DiffUtil.ItemCallback<IdentityProviders.Providers>(){
        override fun areItemsTheSame(oldItem: IdentityProviders.Providers, newItem: IdentityProviders.Providers): Boolean {
            return oldItem != newItem

        }

        override fun areContentsTheSame(oldItem: IdentityProviders.Providers, newItem: IdentityProviders.Providers): Boolean {
            return oldItem != newItem
        }

    }


}