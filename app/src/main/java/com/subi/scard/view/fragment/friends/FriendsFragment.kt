package com.subi.scard.view.fragment.friends

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentFriendsBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.*
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.FriendsAdapter
import com.subi.scard.view.adapter.HealthAdapter
import com.subi.scard.view.fragment.show_card.ShowCardFragment

@Suppress("DEPRECATION")
class FriendsFragment : BaseBindingFragment<FragmentFriendsBinding, FriendsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: FriendsViewmodel
        get() = ViewModelProviders.of(this).get(FriendsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_friends
    var adapterX: FriendsAdapter? = null

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        ShowCardFragment.isShowCard = true
        //Load list
        viewDataBinding?.rcvHome?.apply {
            adapterX = FriendsAdapter(viewModel.list){clickItem(it)}
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
        val bundle = bundleOf("id" to item.id?.replace(Constants.FRIEND_TYPE.FRIEND,""))
        findNavController().navigate(R.id.action_friendsFragment_to_showCardFragment, bundle)
    }

    fun clickDelete(id: String) {
        viewModel.deleteItem(id)
    }

    //Không cho sửa
    fun clickEdit(item: Item) {
//        viewModel.editItem(item)
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
        toolbarTitleAndBack(Constants.TITLE.FRIENDS)
    }
}