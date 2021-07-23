package com.subi.scard.view.fragment.info

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.Item
import com.subi.scard.utils.ChauManager
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class InfoViewmodel : BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null
    var isEmty = ObservableBoolean(true)
    var emty = ObservableField("Bạn chưa có thẻ cá nhân nào")
    val listInfo = arrayOf(
        Constants.INFO_TYPE.CCCD,
        Constants.INFO_TYPE.PASSPORT,
    )

    fun insertItem() {
        context?.let {
            ChauManager.setupViewInsertNew(it, listInfo, Constants.ITEM_TYPE.INFO) {
                insert(it)
            }
        }
    }

    fun editItem(item: Item) {

        val listSpinner = mutableListOf<String>()

        listInfo.forEach { mList ->
            var check = true
            for (i in 0 until list.size) {
                if (mList == list[i].title) {
                    check = false
                    break
                }
            }
            if(check) listSpinner.add(mList)
        }

        listSpinner.add(0, item.title!!)

        ChauManager.setupViewEditNew(context!!, listSpinner, Constants.ITEM_TYPE.INFO, item) {
            edit(it)
        }
    }

    private fun edit(item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().editItemById(
                    item.id, item.title, item.description, item.type, item.idUser, item.status
                )
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it.equals("success")) {
                            load()
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

    fun deleteItem(id: String) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().deleteItemById(id)
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it.equals("success")) {
                            load()
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

    private fun insert(item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().insertItem(
                    item.id, item.title, item.description, item.type, item.idUser, item.status
                )
                if (res.isSuccessful) {
                    load()
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }

    fun load() {
        val idUser = context?.let { Utils.getIdUser(it) }
        //Get data
        viewModelScope.launch {
            try {
                val response = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser ?: "111", Constants.ITEM_TYPE.INFO)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        list.clear()
                        response.body()?.getAllList?.let {
                            list.addAll(it)
                            Utils.log(TAG, "size: ${list.size}")
                        }
                        if (!list.isEmpty()){
                            isEmty.set(false)
                        }
                        else{
                            isEmty.set(true)
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
}