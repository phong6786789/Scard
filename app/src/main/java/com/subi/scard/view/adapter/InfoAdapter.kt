package com.subi.scard.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.R
import com.subi.scard.databinding.ItemInfoBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants

class InfoAdapter(
    var items: List<Item>,
    val action:(Item) -> Unit
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
       ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                                                                                              )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
        setFadeAnimation(holder.itemView)
    }

    inner class ViewHolder(var binding:ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(item: Item) {
            binding.apply {
                cardView.setOnLongClickListener {
                    action(item)
                    true
                }

                try {
                    val des = item.description?.split("@")

                    title = item.title
                    hoTen = des?.get(1)
                    maSo = des?.get(0)
                } catch (e: Exception) {
                }

                when(item.title){
                    Constants.INFO_TYPE.CCCD ->{
                        layout.setBackgroundResource(R.drawable.round_info)
                        imageInfo.setBackgroundResource(R.drawable.ubnd_logo)
                    }
                    Constants.INFO_TYPE.PASSPORT ->{
                        layout.setBackgroundResource(R.drawable.round_passport)
                        imageInfo.setBackgroundResource(R.drawable.passport)
                    }

                    else -> cardView.setCardBackgroundColor(R.drawable.bg)
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
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
}