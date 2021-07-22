package com.subi.scard.view.activity.loginGG

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LoginAdapter(fm: FragmentManager?, totalTabs: Int) : FragmentStatePagerAdapter(
    fm!!, totalTabs
) {
    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> LoginTabFragment()
            else -> SignupTabFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title = "Đăng nhập"
            1 -> title = "Đăng ký"
        }
        return title
    }
}