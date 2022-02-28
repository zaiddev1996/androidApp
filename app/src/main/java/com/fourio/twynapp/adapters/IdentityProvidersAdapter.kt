package com.fourio.twynapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.databinding.IdentityProviderItemBinding


class IdentityProvidersAdapter(private val callback: (Int) -> Unit) : ListAdapter<Boolean, IdentityProvidersAdapter.ViewHolder>(DiffCallback()){


    class ViewHolder(private val binding: IdentityProviderItemBinding, private val callback: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Boolean){
            binding.apply {
                if (item){
                    binding.cvTick.visibility = View.VISIBLE
                }else {
                    binding.cvTick.visibility = View.GONE
                }

                binding.root.setOnClickListener {
                    callback(layoutPosition)
                }


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
        holder.setIsRecyclable(false)
    }

    class  DiffCallback: DiffUtil.ItemCallback<Boolean>(){
        override fun areItemsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
            return oldItem != newItem

        }

        override fun areContentsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
            return oldItem != newItem
        }

    }


}