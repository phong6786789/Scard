package com.subi.scard.view.fragment.mxh

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentMXHBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.*
import com.subi.scard.view.adapter.MXHAdapter

@Suppress("DEPRECATION")
class MXHFragment : BaseBindingFragment<FragmentMXHBinding, MXHViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: MXHViewmodel
        get() = ViewModelProviders.of(this).get(MXHViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_m_x_h

    var adapterMXH: MXHAdapter? = null


    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        //Load list
        viewDataBinding?.rcvHome?.apply {
            adapterMXH = MXHAdapter(context, viewModel.list) { clickItem(it) }
            adapter = adapterMXH
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
        adapterMXH?.notifyItemChanged(position)
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
        viewModel.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.MXH)
        right?.visibility = View.VISIBLE
        //Button add
        right?.setOnClickListener {
            viewModel.insertItem()
        }
    }
}