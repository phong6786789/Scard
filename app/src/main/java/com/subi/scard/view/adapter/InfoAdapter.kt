package com.subi.scard.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Utils

class InfoAdapter(
    var items: List<Item>,
    val action:(Item) -> Unit
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
       ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                                                                                              )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
    }

    inner class ViewHolder(var binding:ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(item: Item) {
            binding.apply {
                setVariable(BR.viewmodel, item)
                executePendingBindings()
                cardView.setOnLongClickListener {
                    action(item)
                    true
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setNewData(newItems: List<Item>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClickItem(value: Item)
    }
}