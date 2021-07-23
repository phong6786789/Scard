package com.subi.scard.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.R
import com.subi.scard.databinding.ItemBankBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants

class BankAdapter(
    var items: List<Item>,
    val action:(Item) -> Unit
) : RecyclerView.Adapter<BankAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemBankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                                                                                              )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
        setFadeAnimation(holder.itemView)
    }

    inner class ViewHolder(var binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(item: Item) {
            binding.apply {
                cardView.setOnLongClickListener {
                    action(item)
                    true
                }

                val des = item.description?.split("@")

                title = item.title
                hoTen = des?.get(1)
                soThe = des?.get(0)

                when(item.title){
                    Constants.BANK_TYPE.AGRIBANK -> imageBank.setImageResource(R.drawable.icon_agri)
                    Constants.BANK_TYPE.SACOMBANK -> imageBank.setImageResource(R.drawable.icon_scb)
                    Constants.BANK_TYPE.VIETCOMBANK -> imageBank.setImageResource(R.drawable.icon_vcb)
                    Constants.BANK_TYPE.VIETTINBANK -> imageBank.setImageResource(R.drawable.icon_vtb)
                    Constants.BANK_TYPE.TECHCOMBANK-> imageBank.setImageResource(R.drawable.icon_tcb)

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
        fun onClickItem(value: Item){

        }
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
}