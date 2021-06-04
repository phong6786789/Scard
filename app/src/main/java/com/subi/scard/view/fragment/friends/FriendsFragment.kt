package com.subi.scard.view.fragment.friends

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentFriendsBinding
import com.subi.scard.databinding.FragmentScanQRBinding
import com.subi.scard.utils.Constants
import com.subi.scard.view.fragment.qr_code.QRViewmodel

@Suppress("DEPRECATION")
class FriendsFragment : BaseBindingFragment<FragmentFriendsBinding, FriendsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: FriendsViewmodel
        get() = ViewModelProviders.of(this).get(FriendsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_friends

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constants.TITLE.SETTINGS
    }
}