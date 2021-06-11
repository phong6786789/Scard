package com.subi.scard.view.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentSettingsBinding
import com.subi.scard.utils.Constants

@Suppress("DEPRECATION")
class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding, SettingsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: SettingsViewmodel
        get() = ViewModelProviders.of(this).get(SettingsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_settings

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarOnlyTitle(Constants.TITLE.SETTINGS)
    }

}