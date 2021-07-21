package com.subi.scard.view.fragment.friends

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentFriendsBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.FriendsAdapter

@Suppress("DEPRECATION")
class FriendsFragment : BaseBindingFragment<FragmentFriendsBinding, FriendsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: FriendsViewmodel
        get() = ViewModelProviders.of(this).get(FriendsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_friends

    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        //Load list
        viewModel.load(context?.let { Utils.getIdUser(it) } ?:"")
        viewDataBinding?.rcvHome?.apply {
            adapter = FriendsAdapter(viewModel.list){clickItem(it)}
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
        }
    }

    fun clickItem(item: Item){
        val bundle = bundleOf("id" to item.id?.replace(Constants.FRIEND_TYPE.FRIEND,""))
        findNavController().navigate(R.id.action_friendsFragment_to_showCardFragment, bundle)
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.FRIENDS)
    }
}