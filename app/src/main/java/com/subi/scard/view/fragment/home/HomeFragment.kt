package com.subi.scard.view.fragment.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentHomeMainBinding
import com.subi.scard.model.CustomItem
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.adapter.CustomMenuAdapter
import com.subi.scard.view.fragment.show_card.ShowCardFragment


@Suppress("DEPRECATION")
class HomeFragment : BaseBindingFragment<FragmentHomeMainBinding, HomeViewmodel>(),
    View.OnClickListener {
    val TAG = "HomeFragmentTEST"
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: HomeViewmodel
        get() = ViewModelProviders.of(this).get(HomeViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_home_main


    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        ShowCardFragment.isShowCard = false
        val currentUser = FirebaseAuth.getInstance().currentUser
        viewModel.context = requireContext()
        viewModel.load()

        if (currentUser != null) {
            Utils.log(TAG, "UID: ${FirebaseAuth.getInstance().currentUser?.uid.toString()}")
        }

        //Load list menu
        viewDataBinding?.rcvMenu?.apply {
            adapter = CustomMenuAdapter(viewModel.list_menu, onItemClickListener())
            layoutManager = GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            hasFixedSize()
        }

        //filp animation card
        viewDataBinding?.cardview2?.setOnClickListener {
            Utils.log(TAG, "card click")
            val oa1 = ObjectAnimator.ofFloat(it, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(viewDataBinding?.cardview, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    it.visibility = View.GONE
                    viewDataBinding?.cardview?.visibility = View.VISIBLE
                    oa2.start()
                }
            })
            oa1.start()
        }

        viewDataBinding?.cardview?.setOnClickListener {
            Utils.log(TAG, "card click")
            val oa1 = ObjectAnimator.ofFloat(it, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(viewDataBinding?.cardview2, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    it.visibility = View.GONE
                    viewDataBinding?.cardview2?.visibility = View.VISIBLE
                    oa2.start()
                }
            })
            oa1.start()
        }
    }

    private fun onItemClickListener() = object : CustomMenuAdapter.OnItemClickListener {
        override fun onClickItem(value: CustomItem, i: Int) {
            var idx: Int = when (i) {
                0 -> R.id.action_homeFragment_to_infoFragment
                1 -> R.id.action_homeFragment_to_MXHFragment
                2 -> R.id.action_homeFragment_to_healthFragment
                3 -> R.id.action_homeFragment_to_bankFragment
                4 -> R.id.action_homeFragment_to_friendsFragment
                5 -> R.id.action_homeFragment_to_moreFragment
                else -> R.id.scanQRFragment
            }
            resetBottomNav()
            findNavController().navigate(idx)
        }

    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onClick(v: View?) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarOnlyTitle(Constants.TITLE.HOME)
    }
}