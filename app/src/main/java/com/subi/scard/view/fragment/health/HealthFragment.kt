package com.subi.scard.view.fragment.health

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHealthBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.ChauManager
import com.subi.scard.utils.Constants
import com.subi.scard.view.adapter.HealthAdapter

@Suppress("DEPRECATION")
class HealthFragment : BaseBindingFragment<FragmentHealthBinding, HealthViewmodel>(){
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: HealthViewmodel
        get() = ViewModelProviders.of(this).get(HealthViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_health

    var adapterX: HealthAdapter? = null

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        //Load list

        viewDataBinding?.rcvHome?.apply {
            adapterX = HealthAdapter(viewModel.list){clickItem(it)}
                adapter = adapterX
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
        toolbarTitleAndBack(Constants.TITLE.HEALTH)
    }
}