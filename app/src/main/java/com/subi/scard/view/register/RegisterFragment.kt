package com.subi.scard.view.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentLoginBinding
import com.subi.scard.databinding.FragmentRegisterBinding

class RegisterFragment :  BaseBindingFragment<FragmentRegisterBinding, RegisterViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: RegisterViewmodel
        get() = ViewModelProviders.of(this).get(RegisterViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_register

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = "ĐĂNG KÝ"
    }
}