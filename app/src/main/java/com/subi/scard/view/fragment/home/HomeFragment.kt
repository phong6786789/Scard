package com.subi.scard.view.fragment.home

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHomeBinding
import com.subi.scard.utils.Constants

@Suppress("DEPRECATION")
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: HomeViewmodel
        get() = ViewModelProviders.of(this).get(HomeViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_home



    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constants.TITLE.HOME
    }


}