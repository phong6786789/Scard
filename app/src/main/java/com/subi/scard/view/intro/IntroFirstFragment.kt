package com.subi.scard.view.intro

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentIntroFirstBinding

class IntroFirstFragment : BaseBindingFragment<FragmentIntroFirstBinding, IntroFirstViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: IntroFirstViewmodel
        get() =ViewModelProviders.of(this).get(IntroFirstViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_intro_first

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }
}