package com.subi.scard.view.adapter


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.databinding.ItemMxhBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.view.fragment.show_card.ShowCardFragment


class MXHAdapter(
    var context: Context,
    var items: List<Item>,
    val clickItem: (Item) -> Unit
) : RecyclerView.Adapter<MXHAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMxhBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    @UiThread
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(context, items[position])
        setFadeAnimation(holder.itemView)
    }

    inner class ViewHolder(var binding: ItemMxhBinding) : RecyclerView.ViewHolder(binding.root) {
        @UiThread
        fun binData(context: Context, item: Item) {
            binding.apply {
                setVariable(BR.viewmodel, item)
                executePendingBindings()

                if (ShowCardFragment.isShowCard){
                    cardView3.visibility = View.VISIBLE
                    cardView.setCardBackgroundColor(Color.TRANSPARENT);
                    cardView.cardElevation = 0F;
                    tvTitle.setTextColor(Color.WHITE)
                    lnBackground.setBackgroundResource(0)
                    tvTitle.textSize = 18f
                }

                cardView.setOnLongClickListener {
                    clickItem(item)
                    true
                }
                cardView.setOnClickListener {
                    var url = ""
                    url = when (item.title?.toLowerCase()) {
                        Constants.SOCIAL_TYPE.FACEBOOK.toLowerCase() -> "fb://facewebmodal/f?href=https://www.facebook.com/"
                        Constants.SOCIAL_TYPE.ZALO.toLowerCase() -> "https://zalo.me/"
                        Constants.SOCIAL_TYPE.INSTAGRAM.toLowerCase() -> "https://instagram.com/"
                        Constants.SOCIAL_TYPE.TIKTOK.toLowerCase() -> "https://tiktok.com/@"
                        Constants.SOCIAL_TYPE.YOUTUBE.toLowerCase() -> "https://www.youtube.com/channel/"
                        Constants.SOCIAL_TYPE.EMAIL.toLowerCase() -> "mail"
                        Constants.SOCIAL_TYPE.TWITTER.toLowerCase() -> "https://twitter.com/"
                        Constants.SOCIAL_TYPE.SKYPE.toLowerCase() -> "skype"
                        else -> ""
                    }

                    Log.d("TEST", url + item.description)
                    try {
                        var intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        if (url == "skype") {
                            intent.data = Uri.parse("skype:" + item.description + "?chat")
                            context.startActivity(intent)
                        } else if (url == "mail") {
                            val emailIntent = Intent(
                                Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", item.description, null
                                )
                            )
                            context.startActivity(
                                Intent.createChooser(
                                    emailIntent,
                                    "Send email..."
                                )
                            )
                        } else {
                            intent.data = Uri.parse(url + item.description)
                            context.startActivity(intent)
                        }
                    } catch (e: Exception) {
                    }
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

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 1000
        view.startAnimation(anim)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }
}