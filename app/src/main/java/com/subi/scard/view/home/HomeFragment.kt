package com.subi.scard.view.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHomeBinding

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
        activity?.title = "S-CARD"
    }


}