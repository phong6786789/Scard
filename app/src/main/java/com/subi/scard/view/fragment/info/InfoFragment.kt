package com.subi.scard.view.fragment.info

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentInfoBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.*
import com.subi.scard.view.adapter.InfoAdapter
import com.subi.scard.view.adapter.MXHAdapter

@Suppress("DEPRECATION")
class InfoFragment : BaseBindingFragment<FragmentInfoBinding, InfoViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: InfoViewmodel
        get() = ViewModelProviders.of(this).get(InfoViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_info

    var adapterX: InfoAdapter? = null

    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        //Load list
        viewDataBinding?.rcvHome?.apply {
            adapterX = InfoAdapter(viewModel.list) { clickItem(it) }
            adapter = adapterX
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            val itemTouchHelper = ItemTouchHelper(
                ChauManager.setupItemTouchHelper(
                    viewModel.list,
                    requireContext(),
                    { clickDelete(it) },
                    { clickEdit(it) },
                    { clickLoadRecycView(it) })
            )
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    fun clickItem(item: Item) {

    }

    fun clickDelete(id: String) {
        viewModel.deleteItem(id)
    }

    fun clickEdit(item: Item) {
        viewModel.editItem(item)
    }

    fun clickLoadRecycView(position: Int) {
        adapterX?.notifyItemChanged(position)
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
        viewModel.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.INFO)
        right?.visibility = View.VISIBLE
        //Button add
        right?.setOnClickListener {
            viewModel.insertItem()
        }
    }

}