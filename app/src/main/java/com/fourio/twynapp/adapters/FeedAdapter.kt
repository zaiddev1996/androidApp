package com.fourio.twynapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fourio.twynapp.databinding.FeedItemBinding
import com.fourio.twynapp.model.IdentityFeed
import androidx.constraintlayout.widget.ConstraintSet
import com.fourio.twynapp.R
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.decode


class FeedAdapter(private val context: Context, private val callback: (Int) -> Unit) :
    ListAdapter<IdentityFeed.Requests, FeedAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: FeedItemBinding,
        private val context: Context,
        private val callback: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IdentityFeed.Requests) {
            binding.apply {
                if (item.request_type == "Face") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_face_login))
                } else if (item.request_type == "Finger") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_finger_print_login))
                } else if (item.request_type == "Document") {
                    ivRequestType.setImageDrawable(context.getDrawable(R.drawable.ic_document))

                } else if (item.request_type == "Offer") {
                    ivRequestType.setImageDrawable(context.getDrawable(com.fourio.twynapp.R.drawable.ic_offer))
                    cvAccept.visibility = View.INVISIBLE
                    cvDecline.visibility = View.INVISIBLE
                    viewDividerHorz.visibility = View.INVISIBLE
                    viewDividerVert.visibility = View.INVISIBLE
                    ivTick.visibility = View.INVISIBLE
                    tvAccept.visibility = View.INVISIBLE
                    ivCross.visibility = View.INVISIBLE
                    tvDecline.visibility = View.INVISIBLE
                    val constraintLayout: ConstraintLayout = binding.parentLayout
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(constraintLayout)
                    constraintSet.connect(
                        binding.cvRequest.id,
                        ConstraintSet.BOTTOM,
                        binding.tvRequest.id,
                        ConstraintSet.BOTTOM,
                        0
                    )

                    constraintSet.applyTo(constraintLayout)
                    cvRequestImg.radius =
                        context.resources.getDimensionPixelSize(R.dimen._5sdp).toFloat()
//                    cvRequest.pad
                }
                tvRequestTitle.text = item.request_label
                tvRequest.text = item.request_description
                val decodedString: ByteArray = decode(item.request_sender_logo, DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                ivSenderLogo.setImageBitmap(decodedByte)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, context, callback)
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