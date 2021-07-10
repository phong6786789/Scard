package com.subi.scard.view.fragment.friends

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import com.subi.scard.view.activity.loginGG.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendsViewmodel: BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null
    var idUser = "111"

    init {
        load()
    }

    fun load() {
        //Check have account current
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser != null) {
//            idUser = currentUser.uid
//        } else {
//            context?.let { Utils.tempNext(it, LoginActivity::class.java) }
//        }
        //Get data
        viewModelScope.launch {
            try {
                Utils.log(TAG, "Call data list")
                val response = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.FRIEND)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        list.clear()
                        response.body()?.getAllList?.let {
                            list.addAll(it)
                            Utils.log(TAG, "size: ${list.toString()}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response.errorBody()}")
                    }
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }

    fun deleteItem(id: String){
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().deleteItemById(id)
                if(res.isSuccessful){
                    res.body()?.status?.let {
                        if(it.equals("success")){
                            load()
                        }
                    }
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            }
            catch (e: Exception){
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }

    fun insert(){
        val title = Constants.FRIEND_TYPE.FRIEND
        insertItem(Item("0", title, title, Constants.ITEM_TYPE.FRIEND, "111", "qwa"))
    }

    fun insertItem(item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().insertItem(
                    item.title, item.description, item.type, item.idUser, item.status
                )
                if (res.isSuccessful) {
                    load()
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            }catch (e: Exception){
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }
}