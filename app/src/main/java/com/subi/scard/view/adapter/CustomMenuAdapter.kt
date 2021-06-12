package com.subi.scard.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemCustomBinding
import com.subi.scard.model.CustomItem


class CustomMenuAdapter(
    var items: List<CustomItem>,
    var onItemClickListener: OnItemClickListener?
) : RecyclerView.Adapter<CustomMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener, position)
    }

    class ViewHolder(var binding: ItemCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binData(sync: CustomItem, onItemClickListener: OnItemClickListener?, position: Int) {
            binding.apply {
                setVariable(BR.viewmodel, sync)
                executePendingBindings()
            }
            binding.itemCard.setOnClickListener {
                onItemClickListener?.onClickItem(sync, position)
            }
        }

    }

    fun setNewData(newItems: List<CustomItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onClickItem(value: CustomItem, i: Int)
    }


}