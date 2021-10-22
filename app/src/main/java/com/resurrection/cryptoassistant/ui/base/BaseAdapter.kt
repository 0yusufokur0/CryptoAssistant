package com.resurrection.cryptoassistant.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T, viewDataBinding : ViewDataBinding>(
    private var layoutResource: Int,
    var list: ArrayList<T>,
    private var itemId: Int,
    private var onItemClick: (T) -> Unit
) : RecyclerView.Adapter<BaseAdapter.BaseHolder<T>>() {

    lateinit var binding: viewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutResource,
            parent,
            false
        )
        return BaseHolder(binding, itemId,onItemClick )
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class BaseHolder<T>(private var binding: ViewDataBinding, private var itemId: Int,var onItemClick: (T) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(itemId, item)
            itemView.setOnClickListener { onItemClick((item)) }
        }

    }
}

