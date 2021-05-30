package com.subi.scard.view.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentLoginBinding
import com.subi.scard.utils.Constance

class LoginFragment : BaseBindingFragment<FragmentLoginBinding, LoginViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: LoginViewmodel
        get() = ViewModelProviders.of(this).get(LoginViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_login

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        viewDataBinding?.apply {

        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constance.TITLE.LOGIN
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}