package com.subi.scard.view.fragment.mxh

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
class MXHViewmodel : BaseViewModel() {
    val TAG = "MXHViewmodel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null

    var isEmty = ObservableBoolean(true)
    var emty = ObservableField("Chưa có mạng xã hội nào")
    val listSocail = arrayOf(
        Constants.SOCIAL_TYPE.EMAIL,
        Constants.SOCIAL_TYPE.FACEBOOK,
        Constants.SOCIAL_TYPE.INSTAGRAM,
        Constants.SOCIAL_TYPE.SKYPE,
        Constants.SOCIAL_TYPE.TIKTOK,
        Constants.SOCIAL_TYPE.TWITTER,
        Constants.SOCIAL_TYPE.YOUTUBE,
        Constants.SOCIAL_TYPE.ZALO
    )

    fun insertItem() {
        ChauManager.setupViewInsert(context!!, listSocail, Constants.ITEM_TYPE.SOCIAL) {
            insert(it)
        }
    }

    fun editItem(item: Item) {

        val listSpinner = mutableListOf<String>()

        listSocail.forEach { mList ->
            var check = true
            for (i in 0 until list.size) {
                if (mList == list[i].title) {
                    check = false
                    break
                }
            }
            if (check) listSpinner.add(mList)
        }

        listSpinner.add(0, item.title!!)

        ChauManager.setupViewEdit(context!!, listSpinner, Constants.ITEM_TYPE.SOCIAL, item) {
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
                    load()
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
                    when (res.body()?.status.toString()) {
                        Constants.STATUS.SUCCESS -> load()
                        Constants.STATUS.EXIST ->
                            context?.let { Utils.showMess(it, "Mạng xã hội đã tồn tại!") }
                        Constants.STATUS.FAILED ->
                            context?.let {
                                Utils.showMess(it, "Có lỗi xảy ra, không thể thêm MXH mới")
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


    fun load() {
        val idUser = context?.let { Utils.getIdUser(it) }
        //Get data
        viewModelScope.launch {
            try {
                val response = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser ?: "111", Constants.ITEM_TYPE.SOCIAL)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        list.clear()
                        response.body()?.getAllList?.let {
                            list.addAll(it)
                            Utils.log(TAG, "size: ${list.size}")
                        }
                        if (!list.isEmpty()) {
                            isEmty.set(false)
                        } else {
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