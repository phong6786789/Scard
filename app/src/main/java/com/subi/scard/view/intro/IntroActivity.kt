package com.subi.scard.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.subi.scard.R
import com.subi.scard.databinding.ActivityIntroBinding
import com.subi.scard.utils.Utils
import com.subi.scard.view.MainActivity
import com.subi.scard.view.loginGG.LoginActivity

@Suppress("DEPRECATION")
class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private var isLastPage: Boolean = false
    private val images: IntArray = intArrayOf(
        R.drawable.intro1,
        R.drawable.intro2,
        R.drawable.intro3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        binding.lifecycleOwner = this
        binding.tabDots.setupWithViewPager( binding.viewPager, true)
        binding.viewPager.adapter = IntroAdapter(this, images)
//        binding.pageIndicatorView.setViewPager(binding.viewPager)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.buttonNext.setOnClickListener {
            onClickNext()
        }

        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(p0: Int) {
                    // no-op
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                    // no-op
                }

                override fun onPageScrollStateChanged(p0: Int) {
                    when (p0) {
                        ViewPager.SCROLL_STATE_SETTLING -> {
                            if (binding.viewPager.currentItem == images.size - 1) {
                                binding.buttonNext.visibility = View.VISIBLE
                            } else {
                                binding.buttonNext.visibility = View.GONE
                            }
                        }
                        ViewPager.SCROLL_STATE_IDLE -> {
                            isLastPage = binding.viewPager.currentItem == images.size - 1
                        }
                        else -> {
                            // no-op
                        }
                    }
                }
            }
        )
    }

    private fun onClickNext() {
        if (isLastPage) {
            Utils.tempNext(this, MainActivity::class.java)
        } else {
            binding.viewPager.arrowScroll(View.FOCUS_RIGHT)
        }
    }
}
