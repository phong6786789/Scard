package com.subi.scard.view.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentSettingsBinding
import com.subi.scard.model.CustomItem
import com.subi.scard.utils.Constants
import com.subi.scard.view.adapter.CustomMenuAdapter
import com.subi.scard.view.adapter.SettingAdapter

@Suppress("DEPRECATION")
class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding, SettingsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: SettingsViewmodel
        get() = ViewModelProviders.of(this).get(SettingsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_settings

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        //Load list menu
        viewDataBinding?.rcvSetting?.apply {
            adapter = SettingAdapter(viewModel.list_menu, onItemClickListener())
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            hasFixedSize()
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarOnlyTitle(Constants.TITLE.SETTINGS)
    }

    private fun onItemClickListener() = object : SettingAdapter.OnItemClickListener {
        override fun onClickItem(value: CustomItem, i: Int) {
            var idx: Int = when (i) {
                0 -> R.id.infoFragment
                1 -> R.id.MXHFragment
                2 -> R.id.healthFragment
                3 -> R.id.bankFragment
                4 -> R.id.friendsFragment
                5 -> R.id.moreFragment
                else -> R.id.scanQRFragment
            }
            resetBottomNav()
            findNavController().navigate(idx)
        }

    }

}