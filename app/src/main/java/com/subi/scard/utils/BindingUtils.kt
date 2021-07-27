package com.subi.scard.utils


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.UiThread
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.subi.scard.R
import com.subi.scard.model.Item
import com.subi.scard.view.adapter.*
import java.io.InputStream
import java.lang.Exception
import java.net.URL

object BindingUtils {
    @SuppressLint("DefaultLocale")
    @BindingAdapter("setImageBackground")
    @JvmStatic
    fun setImageBackground(imageView: RelativeLayout, title: String) {

        var type = when (title.toLowerCase()) {
            Constants.SOCIAL_TYPE.FACEBOOK.toLowerCase() -> R.drawable.round_fb
            Constants.SOCIAL_TYPE.ZALO.toLowerCase() -> R.drawable.round_zalo
            Constants.SOCIAL_TYPE.INSTAGRAM.toLowerCase() -> R.drawable.round_instagram
            Constants.SOCIAL_TYPE.TIKTOK.toLowerCase() -> R.drawable.round_tiktok
            Constants.SOCIAL_TYPE.YOUTUBE.toLowerCase() -> R.drawable.round_youtube
            Constants.SOCIAL_TYPE.EMAIL.toLowerCase() -> R.drawable.round_mail
            Constants.SOCIAL_TYPE.TWITTER.toLowerCase() -> R.drawable.round_twitter
            Constants.SOCIAL_TYPE.SKYPE.toLowerCase() -> R.drawable.round_skype
            else -> R.mipmap.ic_launcher
        }
        imageView.setBackgroundResource(type)
    }

    @SuppressLint("DefaultLocale")
    @BindingAdapter("setImageItem")
    @JvmStatic
    fun setImageItem(imageView: ImageView, title: String) {

        val type = when (title.toLowerCase()) {
            Constants.SOCIAL_TYPE.FACEBOOK.toLowerCase() -> R.drawable.icon_fb
            Constants.SOCIAL_TYPE.ZALO.toLowerCase() -> R.drawable.icon_zalo
            Constants.SOCIAL_TYPE.INSTAGRAM.toLowerCase() -> R.drawable.icon_instagram
            Constants.SOCIAL_TYPE.TIKTOK.toLowerCase() -> R.drawable.icon_tiktok
            Constants.SOCIAL_TYPE.YOUTUBE.toLowerCase() -> R.drawable.icon_youtube
            Constants.SOCIAL_TYPE.EMAIL.toLowerCase() -> R.drawable.icon_gmail
            Constants.SOCIAL_TYPE.TWITTER.toLowerCase() -> R.drawable.icon_twitter
            Constants.SOCIAL_TYPE.SKYPE.toLowerCase() -> R.drawable.icon_skype
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
        if (adapter != null && adapter is InfoAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
        if (adapter != null && adapter is BankAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
        if (adapter != null && adapter is HealthAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
        if (adapter != null && adapter is FriendsAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }

    @BindingAdapter("setImageResource")
    @JvmStatic
    fun     setImageResource(imageView: ImageView, image: Int) {
        imageView.setImageResource(image)
    }

    @BindingAdapter("setImageFromUrl")
    @JvmStatic
    fun setImageFromUrl(imageView: ImageView, url: String) {
        try {
            Picasso.get().load(url).placeholder(R.drawable.logo).error(R.drawable.logo).into(imageView)
        }
        catch (e:Exception){

        }

    }

    @BindingAdapter("setImageFromUID")
    @JvmStatic
    fun setImageFromUID(imageView: ImageView, uid: String) {
        try {
            val imgUrl = "http://api.qrserver.com/v1/create-qr-code/?data=$uid&size=70x70"
            Picasso.get().load(imgUrl).placeholder(R.drawable.logo).error(R.drawable.logo).into(imageView)

        }
        catch (e:Exception){

        }
    }


}