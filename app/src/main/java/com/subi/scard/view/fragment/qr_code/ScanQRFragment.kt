package com.subi.scard.view.fragment.qr_code

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentScanQRBinding
import com.subi.scard.utils.Constants

@Suppress("DEPRECATION")
class ScanQRFragment : BaseBindingFragment<FragmentScanQRBinding, QRViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: QRViewmodel
        get() = ViewModelProviders.of(this).get(QRViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_scan_q_r

    override fun initVariable(savedInstanceState: Bundle?, view: View) {

    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constants.TITLE.QR
    }
}