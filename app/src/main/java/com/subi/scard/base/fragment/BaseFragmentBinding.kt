package com.subi.scard.base.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.utils.Utils
import com.subi.scard.view.PlashScreen

abstract class BaseBindingFragment<V : ViewDataBinding, M : BaseViewModel> : BaseFragment() {

    var viewDataBinding: V? = null
    var toolbar: RelativeLayout? = null
    var left: ImageView? = null
    var right: ImageView? = null
    var titlex: TextView? = null
    abstract val bindingVariable: Int

    abstract val viewModel: M

    var itemLogout: MenuItem? = null
    var itemQr: MenuItem? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        initVariable(savedInstanceState, viewDataBinding?.root!!)
        initData(savedInstanceState, viewDataBinding?.root!!)

        return viewDataBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseBindingFragment
        }
        toolbar = view.findViewById(R.id.toolbar)
        left = toolbar?.findViewById(R.id.imageLeft)
        right = toolbar?.findViewById(R.id.imageRight)
        titlex = toolbar?.findViewById(R.id.textTitle)
    }

    fun logOut() {
        //Clear all sharePrefs
        context?.let {
            Utils.clearAllSharePrefs(it)
            Utils.tempNext(it, PlashScreen::class.java)
        }
    }

    fun onBack() {
        activity?.onBackPressed()
    }

    //Option toolbar
    fun toolbarTitleAndBack(title: String) {
        titlex?.text = title
        left?.setOnClickListener {
            onBack()
        }
    }

    fun toolbarOnlyTitle(title: String) {
        titlex?.text = title
        left?.visibility = View.GONE
    }

    fun resetBottomNav(){
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)?.show(-1)
    }
}