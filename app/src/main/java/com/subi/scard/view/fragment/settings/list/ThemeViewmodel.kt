package com.subi.scard.view.fragment.settings.list

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem
import com.subi.scard.utils.Constants
import com.subi.scard.utils.Utils
import kotlinx.coroutines.launch

class ThemeViewmodel: BaseViewModel() {
    var context: Context? = null
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()
    val list_qr = mutableListOf<String>()
    val status = MutableLiveData<String?>()

    val TAG = "LogThemeViewmodel"

    init {
        list_menu.addAll(
            listOf(
                CustomItem(1, R.drawable.ic_baseline_api_24, "Giao diện thẻ card QR"),
                CustomItem(2, R.drawable.ic_baseline_api_24, "Giao diện item MXH"),
                CustomItem(3, R.drawable.ic_baseline_api_24, "Màu sắc hệ thống app"),
                CustomItem(4, R.drawable.ic_baseline_api_24, "Di chuyển các nút")
            )
        )

        list_qr.add(Constants.THEME_COLOR.THEME_1)
        list_qr.add(Constants.THEME_COLOR.THEME_2)
        list_qr.add(Constants.THEME_COLOR.THEME_3)
        list_qr.add(Constants.THEME_COLOR.THEME_4)
        list_qr.add(Constants.THEME_COLOR.THEME_5)
        list_qr.add(Constants.THEME_COLOR.THEME_6)
        list_qr.add(Constants.THEME_COLOR.THEME_7)
        list_qr.add(Constants.THEME_COLOR.THEME_8)
        list_qr.add(Constants.THEME_COLOR.THEME_9)
        list_qr.add(Constants.THEME_COLOR.THEME_10)
        list_qr.add(Constants.THEME_COLOR.THEME_11)
        list_qr.add(Constants.THEME_COLOR.THEME_12)
        list_qr.add(Constants.THEME_COLOR.THEME_13)
        list_qr.add(Constants.THEME_COLOR.THEME_14)
        list_qr.add(Constants.THEME_COLOR.THEME_15)
    }

    fun editThemeColorQrScard(background: String){

        val idUser = context?.let { Utils.getIdUser(it) }

        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().editThemeByIdUserAndType(idUser, background, Constants.THEME_TYPE.QR_CARD)
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it.equals("success")) {
                            status.postValue("success")
                        }else{
                            status.postValue("failed")
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