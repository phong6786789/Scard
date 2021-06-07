package com.subi.scard.base.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.utils.RightInterface
import com.subi.scard.utils.ShowDialog
import com.subi.scard.utils.Utils
import com.subi.scard.view.PlashScreen

abstract class BaseBindingFragment<V : ViewDataBinding, M : BaseViewModel> : BaseFragment() {

    var viewDataBinding: V? = null
    var toolbar: RelativeLayout? = null
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
            toolbar = (view.findViewById(R.id.toolbar))
            setupToolbar()
        }
    }

    fun logOut() {
        //Clear all sharePrefs
        context?.let {
            Utils.clearAllSharePrefs(it)
            Utils.tempNext(it, PlashScreen::class.java)
        }
        //Sign out
    }

    private fun setupToolbar() {
        var qrCode: ImageView? = toolbar?.findViewById(R.id.imageLeft)
        var showCard: ImageView? = toolbar?.findViewById(R.id.imageRight)

        qrCode?.setOnClickListener {
            var dialog: Dialog? = null
            dialog =
                context?.let { it1 ->
                    ShowDialog.Builder(it1)
                        .title("Thông báo")
                        .message("QR đang pphát triển")
                        .setRightButton("ĐÓNG", object : RightInterface {
                            override fun onClick() {
                                dialog?.dismiss()
                            }

                        })
                        .miniDialog()
                }
            dialog?.show()
        }

        showCard?.setOnClickListener {
            var dialog: Dialog? = null
            dialog =
                context?.let { it1 ->
                    ShowDialog.Builder(it1)
                        .title("Thông báo")
                        .message("Show card đang pphát triển")
                        .setRightButton("ĐÓNG", object : RightInterface {
                            override fun onClick() {
                                dialog?.dismiss()
                            }

                        })
                        .miniDialog()
                }
            dialog?.show()
        }
    }

}