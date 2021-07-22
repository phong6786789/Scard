package com.subi.scard.view.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class HomeViewmodel : BaseViewModel() {
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()
    var uid = ObservableField("")
    var name = ObservableField("")
    var image =  ObservableField("")
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

    fun load(){
        val idx = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val namex = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        val imagex = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        uid.set(idx)
        name.set(namex)
        image.set(imagex)
        //Auto add info of User
        if(!idx.equals("null")){
            viewModelScope.launch {
                Log.d("TAG", "test: ${FirebaseAuth.getInstance().currentUser?.email.toString()}")
                val res = BaseNetwork.getInstance().insertItem(
                    (Constants.INFO_TYPE.INFO+idx), namex, imagex, Constants.INFO_TYPE.INFO, idx, "0"
                )
                if (res.isSuccessful) {
                    Utils.log("TAG", "success: ${res.body()?.status}")
                } else {
                    Utils.log("TAG", "failed: ${res.body()}")
                }

            }
        }
    }

}