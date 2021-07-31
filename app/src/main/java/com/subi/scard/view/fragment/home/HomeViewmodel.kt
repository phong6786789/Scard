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
import com.subi.scard.model.Theme
import com.subi.scard.model.ThemeRepo
import com.subi.scard.utils.Constants
import com.subi.scard.utils.SharedPrefs
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
    var image = ObservableField("")
    var context: Context? = null
    var bg = ObservableField("")

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

    fun load() {
        //Load thông tin user
        viewModelScope.launch {
            val idUser = context?.let { Utils.getIdUser(it) } ?: ""
            Log.d("loadUser", "id Hiện tại $idUser")

            val responseTheme = BaseNetwork.getInstance().checkThemeByIdUser(idUser, Constants.THEME_TYPE.QR_CARD)
            withContext(Dispatchers.Main) {
                Utils.log("responseTheme", "response: ${responseTheme.body()}")
                if (responseTheme.isSuccessful) {
                    val theme = responseTheme.body()?.list?.get(0)
                    bg.set(theme?.background)
                } else {
                    Utils.log("TAG", "failed: ${responseTheme.errorBody()}")
                }
            }


            val responseUser = BaseNetwork.getInstance()
                .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.AVATAR)
            withContext(Dispatchers.Main) {
                Utils.log("TAG", "response: ${responseUser.body()}")
                try{
                    if (responseUser.isSuccessful) {
                        var user: Item? = null
                        responseUser.body()?.getAllList?.let {
                            for (xz in it) {
                                user = xz
                            }
                            name.set(user?.title)
                            image.set(user?.description)
                            uid.set(idUser)
                            Log.d("loadUser", "id Hiện tại $idUser")

                            context?.let { it1 -> Utils.saveFullName(it1, user?.title ?: "") }
                            Utils.log("TAG", "user: $user")
                        }
                    } else {
                        Utils.log("TAG", "failed: ${responseUser.errorBody()}")
                    }
                }catch (ex: Exception){

                }

            }
        }
    }

}