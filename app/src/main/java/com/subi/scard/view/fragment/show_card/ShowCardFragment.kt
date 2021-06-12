package com.subi.scard.view.fragment.show_card

import android.content.Context.WINDOW_SERVICE
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProviders
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentShowCardBinding
import com.subi.scard.utils.Constants
import kotlin.properties.Delegates


@Suppress("DEPRECATION")
class ShowCardFragment : BaseBindingFragment<FragmentShowCardBinding, ShowCardViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: ShowCardViewmodel
        get() = ViewModelProviders.of(this).get(ShowCardViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_show_card

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        activity?.title = Constants.TITLE.QR
    }
}