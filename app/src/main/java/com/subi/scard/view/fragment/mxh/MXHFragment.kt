package com.subi.scard.view.fragment.mxh

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentMXHBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.HomeAdapter

@Suppress("DEPRECATION")
class MXHFragment : BaseBindingFragment<FragmentMXHBinding, MXHViewmodel>(){
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: MXHViewmodel
        get() = ViewModelProviders.of(this).get(MXHViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_m_x_h


    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            viewModel.idUser = currentUser.uid
            viewModel.load()
        } else {
            context?.let { Utils.tempNext(it, LoginActivity::class.java) }
        }


        //Load list

        viewDataBinding?.rcvHome?.apply {
            adapter = HomeAdapter(viewModel.list, onItemClickListener())
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    private fun onItemClickListener() = object : HomeAdapter.OnItemClickListener {
        override fun onClickItem(value: Item) {

        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        setTitle(Constants.TITLE.MXH)
        viewModel.context = context
    }
}