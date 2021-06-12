package com.subi.scard.view.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem
import com.subi.scard.model.Item
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class HomeViewmodel : BaseViewModel() {
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()

    init {
        list_menu.addAll(
            listOf(
                CustomItem(1, R.drawable.ic_info, "Cá nhân"),
                CustomItem(2, R.drawable.ic_social, "Mạng xã hội"),
                CustomItem(3, R.drawable.ic_health, "Y tế"),
                CustomItem(4, R.drawable.ic_bank, "Ngân hàng"),
                CustomItem(5, R.drawable.ic_friends, "Bạn bè"),
                CustomItem(6, R.drawable.ic_more, "Thêm")
            )
        )
    }

}