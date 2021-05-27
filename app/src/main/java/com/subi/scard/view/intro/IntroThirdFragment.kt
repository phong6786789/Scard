package com.subi.scard.view.intro

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentIntroFirstBinding
import com.subi.scard.databinding.FragmentIntroThirdBinding

class IntroThirdFragment : BaseBindingFragment<FragmentIntroThirdBinding, IntroThirdViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: IntroThirdViewmodel
        get() =ViewModelProviders.of(this).get(IntroThirdViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_intro_first

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }
}