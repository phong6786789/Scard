package com.subi.scard.view.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHome2Binding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.HomeAdapter

@Suppress("DEPRECATION")
class HomeFragment : BaseBindingFragment<FragmentHome2Binding, HomeViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: HomeViewmodel
        get() = ViewModelProviders.of(this).get(HomeViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_home2


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
        setTitle(Constants.TITLE.HOME)
        viewModel.context = context
    }
}