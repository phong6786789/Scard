package com.subi.scard.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.subi.scard.R

class CustomDropDownAdapter(val context: Context, var dataSource: Array<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        val title = dataSource[position]

        vh.label.text = title

        var type = when (title.toLowerCase()) {
            Constants.SOCIAL_TYPE.FACEBOOK.toLowerCase() -> R.drawable.icon_fb
            Constants.SOCIAL_TYPE.ZALO.toLowerCase() -> R.drawable.icon_zalo
            Constants.SOCIAL_TYPE.INSTAGRAM.toLowerCase() -> R.drawable.icon_instagram
            Constants.SOCIAL_TYPE.TIKTOK.toLowerCase() -> R.drawable.icon_tiktok
            Constants.SOCIAL_TYPE.YOUTUBE.toLowerCase() -> R.drawable.icon_ytb
            Constants.SOCIAL_TYPE.EMAIL.toLowerCase() -> R.drawable.icon_gmail
            Constants.SOCIAL_TYPE.TWITTER.toLowerCase() -> R.drawable.icon_twitter
            Constants.SOCIAL_TYPE.SKYPE.toLowerCase() -> R.drawable.icon_skype

            Constants.INFO_TYPE.CCCD.toLowerCase() -> R.drawable.ubnd_logo
            Constants.INFO_TYPE.PASSPORT.toLowerCase() -> R.drawable.passport

            Constants.BANK_TYPE.AGRIBANK.toLowerCase() -> R.drawable.icon_agri
            Constants.BANK_TYPE.SACOMBANK.toLowerCase() -> R.drawable.icon_scb
            Constants.BANK_TYPE.VIETCOMBANK.toLowerCase() -> R.drawable.icon_vcb
            Constants.BANK_TYPE.VIETTINBANK.toLowerCase() -> R.drawable.icon_vtb
            Constants.BANK_TYPE.TECHCOMBANK.toLowerCase() -> R.drawable.icon_tcb

            else -> R.mipmap.ic_launcher
        }

        vh.img.setBackgroundResource(type)

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView

        init {
            label = row?.findViewById(R.id.text) as TextView
            img = row?.findViewById(R.id.img) as ImageView
        }
    }

}