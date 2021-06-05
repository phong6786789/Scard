package com.subi.scard.view.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.Item
import com.subi.scard.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class HomeViewmodel : BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null
    var idUser = ""

    fun load() {
        try {
            viewModelScope.launch {
                Utils.log(TAG, "Call data list")
                val response = BaseNetwork.getInstance().getAllItemByIdUser(idUser)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val item = response.body()?.getAllList
                        if (item != null) {
                            list.clear()
                            list.addAll(item)
                        }

                    } else {
                        Utils.log(TAG, "failed: ${response.errorBody()}\n")
                    }
                }
            }

        } catch (e: Exception) {
            Utils.log(TAG, "erro: ${e.message}")
        }
    }
}