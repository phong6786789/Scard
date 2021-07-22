package com.subi.scard.view.fragment.friends

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.Item
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendsViewmodel: BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null

    fun load(idUser:String) {
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

    fun deleteItem(context: Context, item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().deleteItemById(item.id?:"")
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it == "success") {
                            Utils.getIdUser(context)?.let { it1 -> load(it1) }
                        }
                    }
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }
}