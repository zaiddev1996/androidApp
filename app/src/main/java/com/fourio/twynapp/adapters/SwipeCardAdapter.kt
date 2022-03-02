package com.fourio.twynapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.databinding.FeedItemBinding
import com.fourio.twynapp.databinding.IdentityProviderItemBinding
import com.fourio.twynapp.databinding.SwipeCardItemBinding


class SwipeCardAdapter(private val callback: (Int) -> Unit) : ListAdapter<String, SwipeCardAdapter.ViewHolder>(DiffCallback()){


    class ViewHolder(private val binding: SwipeCardItemBinding, private val callback: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String){
            binding.apply {


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = SwipeCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, callback)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLeague = getItem(position)
        holder.bind(currentLeague)
        holder.setIsRecyclable(false)
    }

    class  DiffCallback: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem != newItem

        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem != newItem
        }

    }


}