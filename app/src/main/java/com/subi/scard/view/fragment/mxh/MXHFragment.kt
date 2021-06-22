package com.subi.scard.view.fragment.mxh

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentMXHBinding
import com.subi.scard.databinding.LayoutInsertItemBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.view.adapter.MXHAdapter

@Suppress("DEPRECATION")
class MXHFragment : BaseBindingFragment<FragmentMXHBinding, MXHViewmodel>(){
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: MXHViewmodel
        get() = ViewModelProviders.of(this).get(MXHViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_m_x_h


    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        //Load list
        viewDataBinding?.rcvHome?.apply {
            adapter = MXHAdapter(viewModel.list){clickDelete(it)}
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
    }

    fun clickDelete(item: Item){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Do you want delete ${item.title}?")
        builder.setNegativeButton("No"){ d, _ ->
            d.dismiss()
        }
        builder.setPositiveButton("Yes"){ d, _ ->
            viewModel.deleteItem(item.id!!)
            viewModel.load()
            d.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.MXH)
    }
}