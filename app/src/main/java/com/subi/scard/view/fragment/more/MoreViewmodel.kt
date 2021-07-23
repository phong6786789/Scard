package com.subi.scard.view.fragment.more

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.Item
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class MoreViewmodel : BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null
    var isEmty = ObservableBoolean(true)
    var emty = ObservableField("Tạm thời chưa có chức năng khác")
    fun load() {
        val idUser = context?.let { Utils.getIdUser(it) }
        if (!list.isEmpty()) {
            isEmty.set(false)
        } else {
            isEmty.set(true)
        }

    }
}