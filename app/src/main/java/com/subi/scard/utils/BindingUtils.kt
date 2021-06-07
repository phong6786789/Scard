package com.subi.scard.utils


import android.widget.ImageView
import androidx.annotation.UiThread
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.R
import com.subi.scard.model.Item
import com.subi.scard.view.adapter.HomeAdapter

object BindingUtils {
    @BindingAdapter("setImageItem")
    @JvmStatic
    fun setImageItem(imageView: ImageView, title: String) {

        var type = when (title.toLowerCase()) {
            Constants.SOCIAL_TYPE.FACEBOOK -> R.drawable.icon_fb
            Constants.SOCIAL_TYPE.ZALO -> R.drawable.icon_zalo
            Constants.SOCIAL_TYPE.INSTAGRAM -> R.drawable.icon_instagram
            Constants.SOCIAL_TYPE.TIKTOK -> R.drawable.icon_tiktok
            Constants.SOCIAL_TYPE.YOUTUBE -> R.drawable.icon_ytb
            else -> R.mipmap.ic_launcher
        }
        imageView.setImageResource(type)
    }

    @BindingAdapter("setItemAdapter")
    @JvmStatic
    @UiThread
    fun setItemAdapter(recyclerView: RecyclerView, items: List<Item>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null && adapter is HomeAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }
}