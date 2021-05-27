package com.subi.scard.view.intro

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentIntroFirstBinding
import com.subi.scard.databinding.FragmentIntroSecondBinding

class IntroSecondFragment : BaseBindingFragment<FragmentIntroSecondBinding, IntroSecondViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: IntroSecondViewmodel
        get() =ViewModelProviders.of(this).get(IntroSecondViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_intro_first

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }
}