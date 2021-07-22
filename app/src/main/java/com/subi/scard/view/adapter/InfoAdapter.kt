package com.subi.scard.view.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemSocialBinding
import com.subi.scard.model.Item
import com.subi.scard.view.fragment.show_card.ShowCardFragment

class InfoAdapter(
    var items: List<Item>,
    val action:(Item) -> Unit
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
       ItemSocialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                                                                                              )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
        setFadeAnimation(holder.itemView)
    }

    inner class ViewHolder(var binding:ItemSocialBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(item: Item) {
            binding.apply {
                setVariable(BR.viewmodel, item)
                executePendingBindings()
                cardView.setOnLongClickListener {
                    action(item)
                    true
                }
                if (ShowCardFragment.isShowCard){
                    cardView3.visibility = View.VISIBLE
                    cardView.setCardBackgroundColor(Color.TRANSPARENT);
                    lnBackground.setBackgroundResource(0);
                    tvTitle.textSize = 18f
                    cardView.cardElevation = 0F;
                    tvTitle.setTextColor(Color.WHITE)
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