package com.subi.scard.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemMxhBinding
import com.subi.scard.model.Item

class MoreAdapter(
    var items: List<Item>,
    var onItemClickListener: OnItemClickListener?
) : RecyclerView.Adapter<MoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
       ItemMxhBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                                                                                              )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
        setFadeAnimation(holder.itemView)
    }

    class ViewHolder(var binding: ItemMxhBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(item: Item) {
            binding.apply {
                setVariable(BR.viewmodel, item)
                executePendingBindings()
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
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
}