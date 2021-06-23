package com.swetajain.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swetajain.paging3.databinding.RowBinding
import com.swetajain.paging3.network.Result

class PagingAdapter : PagingDataAdapter<Result, PagingAdapter.MyViewHolder>(ResultDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row, parent, false)
        return MyViewHolder(RowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class MyViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Result) {
            binding.textView.text = data.name
            binding.textView2.text = data.status
            Glide.with(binding.imageView)
                .load(data.image)
                .circleCrop()
                .into(binding.imageView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ResultDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return when {
                oldItem.id != newItem.id -> return false
                oldItem.name != newItem.name -> return false
                oldItem.type != newItem.type -> return false
                oldItem.url != newItem.url -> return false
                oldItem.image != newItem.image -> return false
                else -> true
            }
        }
    }
}