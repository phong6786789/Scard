package com.subi.scard.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemSettingBinding
import com.subi.scard.model.CustomItem


class SettingAdapter(
    var items: List<CustomItem>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SettingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener, position)
    }

    class ViewHolder(var binding: ItemSettingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binData(sync: CustomItem, onItemClickListener: OnItemClickListener?, position: Int) {
            binding.apply {
                setVariable(BR.setting, sync)
                executePendingBindings()
            }
            binding.btnSetting.setOnClickListener {
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