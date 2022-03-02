package com.fourio.twynapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.ItemFaqBinding


class FaqAdapter(private val context: Context, private val callback: (Int) -> Unit) :
    ListAdapter<Boolean, FaqAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        private val binding: ItemFaqBinding,
        private val callback: (Int) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Boolean) {
            binding.apply {
                if (item) {
                    binding.faqAnswer.visibility = View.VISIBLE
                    ivIcAdd.setImageDrawable(context.resources.getDrawable(R.drawable.ic_minus))
                } else {
                    binding.faqAnswer.visibility = View.GONE
                    ivIcAdd.setImageDrawable(context.resources.getDrawable(R.drawable.ic_add))
                }

                binding.root.setOnClickListener {
                    callback(layoutPosition)
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, callback,context)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLeague = getItem(position)
        holder.bind(currentLeague)
        holder.setIsRecyclable(false)
    }

    class DiffCallback : DiffUtil.ItemCallback<Boolean>() {
        override fun areItemsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
            return oldItem != newItem

        }

        override fun areContentsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
            return oldItem != newItem
        }

    }


}