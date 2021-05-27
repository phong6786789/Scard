package com.subi.scard.view.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.subi.scard.databinding.ItemTutorialBinding

class IntroAdapter(val context: Context, val images: IntArray) : PagerAdapter() {

    override fun getCount(): Int = images.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemTutorialBinding.inflate(LayoutInflater.from(context), container, false)
        binding.imageView.setImageResource(images[position])

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
