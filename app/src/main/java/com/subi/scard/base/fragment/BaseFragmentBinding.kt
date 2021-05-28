package com.subi.scard.base.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel

abstract class BaseBindingFragment<V : ViewDataBinding, M : BaseViewModel> : BaseFragment() {

    var viewDataBinding: V? = null

    abstract val bindingVariable: Int

    abstract val viewModel: M

    var itemLogout:MenuItem?=null
    var itemQr:MenuItem?=null
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        itemLogout =menu.findItem(R.id.logout)
        itemQr =menu.findItem(R.id.home)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                logOut()
                return true
            }
            R.id.home -> {
                Toast.makeText(activity, "QR", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logOut(){
        //Sign out
        findNavController().navigate(R.id.loginFragment)
    }
    fun homeSetup(){
        itemLogout?.isVisible = true
        itemQr?.isVisible = true
    }
}