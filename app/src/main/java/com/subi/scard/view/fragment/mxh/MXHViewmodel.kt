package com.subi.scard.view.fragment.mxh

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.databinding.LayoutInsertItemBinding
import com.subi.scard.model.Item
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

    fun insert() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            val binding = LayoutInsertItemBinding.inflate(LayoutInflater.from(it))
            binding.tvTitle.text = "THÊM MXH"

            val spinnerAdapter =
                ArrayAdapter(it, android.R.layout.simple_expandable_list_item_1, listSocail)
            binding.spinnerItem.adapter = spinnerAdapter
            builder.setView(binding.root)
            val dialog = builder.create()

            binding.btnInsert.setOnClickListener { v ->

                val link = binding.edtLink.text.toString()
                if (link.isNotEmpty()) {
                    val title = binding.spinnerItem.selectedItem.toString()
                    insertItem(
                        Item(
                            title + Utils.getIdUser(it),
                            title,
                            link,
                            Constants.ITEM_TYPE.SOCIAL,
                            Utils.getIdUser(it),
                            "0"
                        )
                    )
                    dialog.dismiss()
                } else {
                    Utils.showMess(it, "Không được để trống!")
                }

            }
            binding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
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

    fun insertItem(item: Item) {
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
                    .getAllItemByIdUserAndType(idUser ?: "", Constants.ITEM_TYPE.SOCIAL)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        list.clear()
                        response.body()?.getAllList?.let {
                            list.addAll(it)
                            Utils.log(TAG, "size: ${list.size}")
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