package com.subi.scard.view.fragment.settings.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentSettingsBinding
import com.subi.scard.model.CustomItem
import com.subi.scard.utils.ChauManager
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.adapter.SettingAdapter

@Suppress("DEPRECATION")
class ThemeFragment : BaseBindingFragment<FragmentSettingsBinding, ThemeViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: ThemeViewmodel
        get() = ViewModelProviders.of(this).get(ThemeViewmodel::class.java)
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
        viewModel.context = context
        viewModel.status.observe(this){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarOnlyTitle(Constants.TITLE.THEME)
    }

    private fun onItemClickListener() = object : SettingAdapter.OnItemClickListener {
        override fun onClickItem(value: CustomItem, i: Int) {
            when (value.id) {
                1 -> {
                    ChauManager.setupThemeQR(requireContext(), viewModel.list_qr){
                        viewModel.editThemeColorQrScard(it)
                    }
                }
                2 -> Toast.makeText(requireContext(), value.title, Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(requireContext(), value.title, Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(requireContext(), value.title, Toast.LENGTH_SHORT).show()
            }
//                resetBottomNav()
        }

    }

}