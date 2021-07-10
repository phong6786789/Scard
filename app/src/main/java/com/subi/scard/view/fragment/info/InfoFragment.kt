package com.subi.scard.view.fragment.info

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentInfoBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.view.adapter.InfoAdapter

@Suppress("DEPRECATION")
class InfoFragment : BaseBindingFragment<FragmentInfoBinding, InfoViewmodel>(){
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: InfoViewmodel
        get() = ViewModelProviders.of(this).get(InfoViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_info


    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        //Load list
        viewDataBinding?.rcvHome?.apply {
            adapter = InfoAdapter(viewModel.list){clickDelete(it)}
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    fun clickDelete(item: Item){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Do you want delete ${item.title}?")
        builder.setNegativeButton("No"){ d, _ ->
            d.dismiss()
        }
        builder.setPositiveButton("Yes"){ d, _ ->
            viewModel.deleteItem(item.id!!)
            d.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.INFO)
    }
}