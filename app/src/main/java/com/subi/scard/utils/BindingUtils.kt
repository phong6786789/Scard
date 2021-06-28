package com.subi.scard.utils


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.UiThread
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.subi.scard.R
import com.subi.scard.model.Item
import com.subi.scard.view.adapter.MXHAdapter
import java.io.InputStream
import java.lang.Exception
import java.net.URL

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
            Constants.SOCIAL_TYPE.EMAIL -> R.drawable.icon_gmail
            Constants.SOCIAL_TYPE.TWITTER -> R.drawable.icon_twitter
            Constants.SOCIAL_TYPE.SKYPE -> R.drawable.icon_skype
            else -> R.mipmap.ic_launcher
        }
        imageView.setImageResource(type)
    }

    @BindingAdapter("setItemAdapter")
    @JvmStatic
    @UiThread
    fun setItemAdapter(recyclerView: RecyclerView, items: List<Item>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null && adapter is MXHAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }

    @BindingAdapter("setImageResource")
    @JvmStatic
    fun setImageResource(imageView: ImageView, image: Int) {
        imageView.setImageResource(image)
    }

    @BindingAdapter("setImageFromUrl")
    @JvmStatic
    fun setImageFromUrl(imageView: ImageView, url: String) {
//        try {
//            val imgUrl = "http://api.qrserver.com/v1/create-qr-code/?data=$url&size=100x100"
//            try {
//                val iStream = URL(imgUrl).content as InputStream
//                val drawable = Drawable.createFromStream(iStream, "img");
//                if (drawable!=null){
//                    imageView.setImageDrawable(drawable)
//                }
//                else{
//                    imageView.setImageResource(R.drawable.logo)
//                }
//            } catch (e:Exception) {
//                e.printStackTrace()
//            }
//        }
//        catch (e:Exception){
//
//        }
    }
}