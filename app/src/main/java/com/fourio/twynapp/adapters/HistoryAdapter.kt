package com.fourio.twynapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.R
import com.fourio.twynapp.databinding.FeedItemBinding
import com.fourio.twynapp.databinding.HistoryItemBinding
import com.fourio.twynapp.databinding.IdentityProviderItemBinding
import com.fourio.twynapp.model.IdentityFeed


class HistoryAdapter(private val context: Context, private val callback: (Int) -> Unit) :
    ListAdapter<IdentityFeed.Requests, HistoryAdapter.ViewHolder>(DiffCallback()) {


    class ViewHolder(
        private val binding: HistoryItemBinding,
        private val callback: (Int) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IdentityFeed.Requests) {
            binding.apply {
                if (layoutPosition == 0) {
                    binding.viewVerticleBarTop.visibility = View.GONE
                }
                if (item.request_type == "Face") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_face_login))
                } else if (item.request_type == "Finger") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_finger_print_login))
                } else if (item.request_type == "Document") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_document))
                } else if (item.request_type == "Offer") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_offer))
                }
                tvRequest.text = item.request_label
                tvDate.text = item.request_date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, callback, context)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLeague = getItem(position)
        holder.bind(currentLeague)
        holder.setIsRecyclable(false)
    }

    class DiffCallback : DiffUtil.ItemCallback<IdentityFeed.Requests>() {
        override fun areItemsTheSame(
            oldItem: IdentityFeed.Requests,
            newItem: IdentityFeed.Requests
        ): Boolean {
            return oldItem != newItem

        }

        override fun areContentsTheSame(
            oldItem: IdentityFeed.Requests,
            newItem: IdentityFeed.Requests
        ): Boolean {
            return oldItem != newItem
        }

    }


}