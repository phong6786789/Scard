package com.subi.scard.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.databinding.ItemThemeQrBinding


class QRAdapter(
    var items: List<String>,
    val action:(String) -> Unit
) : RecyclerView.Adapter<QRAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemThemeQrBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
    }

    inner class ViewHolder(var binding: ItemThemeQrBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binData(item: String) {
            binding.apply {
                bg = item
                cardView.setOnClickListener {
                    action(item)
                }
            }
        }

    }

    fun setNewData(newItems: List<String>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}