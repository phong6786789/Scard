package com.subi.scard.view.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHomeMainBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.HomeAdapter

@Suppress("DEPRECATION")
class HomeFragment : BaseBindingFragment<FragmentHomeMainBinding, HomeViewmodel>(),
    View.OnClickListener {
    override val bindingVariable: Int
        get() = BR._all
    override val viewModel: HomeViewmodel
        get() = ViewModelProviders.of(this).get(HomeViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_home_main


    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            context?.let { Utils.tempNext(it, LoginActivity::class.java) }
        }


        //Load list

//        viewDataBinding?.rcvHome?.apply {
//            adapter = HomeAdapter(viewModel.list, onItemClickListener())
//            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            hasFixedSize()
//        }
    }

    private fun onItemClickListener() = object : HomeAdapter.OnItemClickListener {
        override fun onClickItem(value: Item) {

        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        setTitle(Constants.TITLE.HOME)
//        viewModel.context = context
        viewDataBinding?.apply {
            btBanking.setOnClickListener(this@HomeFragment)
            btGioitieu.setOnClickListener(this@HomeFragment)
            btMayanh.setOnClickListener(this@HomeFragment)
            btMxh.setOnClickListener(this@HomeFragment)
            btSucKhoe.setOnClickListener(this@HomeFragment)
            btXemthem.setOnClickListener(this@HomeFragment)
        }
    }

    override fun onClick(v: View?) {
        var idx: Int = when (v?.id) {
            R.id.bt_Mxh -> R.id.MXHFragment
            R.id.bt_SucKhoe -> R.id.scanQRFragment
            R.id.bt_banking -> R.id.scanQRFragment
            R.id.bt_gioitieu -> R.id.scanQRFragment
            R.id.bt_mayanh -> R.id.scanQRFragment
            R.id.bt_xemthem -> R.id.scanQRFragment
            else -> R.id.scanQRFragment
        }
        findNavController().navigate(idx)
    }
}