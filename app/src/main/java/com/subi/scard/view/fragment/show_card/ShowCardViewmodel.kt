package com.subi.scard.view.fragment.show_card

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
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

class ShowCardViewmodel: BaseViewModel() {
    val fullname = ObservableField("")
    val email = ObservableField("")
    val avatar = ObservableField("")
    val TAG = "ShowCardViewmodel"
    val listInfo: ObservableList<Item> = ObservableArrayList()
    val listSocial: ObservableList<Item> = ObservableArrayList()
    val listBank: ObservableList<Item> = ObservableArrayList()
    val listHealth: ObservableList<Item> = ObservableArrayList()

    fun load(context:Context, idUser:String){
        Log.d(TAG, "Load for id: $idUser")

        //Add friend after scan

        viewModelScope.launch {
            try {
                Utils.log(TAG, "uid: $idUser")

                //Set ảnh đại diện và thông tin cá nhân
                val responseUser = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.AVATAR)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${responseUser.body()}")
                    if (responseUser.isSuccessful) {
                        listSocial.clear()
                        var user : Item? =null
                        responseUser.body()?.getAllList?.let {
                            for (xz in it){
                                 user = xz
                            }
                            fullname.set(user?.title)
                                    avatar.set(user?.description)
                                    //Tránh trường hợp add friend chính bản thân
                                    if (user?.idUser!=Utils.getIdUser(context)){
                                        viewModelScope.launch {
                                            try {
                                                val res = BaseNetwork.getInstance().insertItem(
                                                    (Constants.FRIEND_TYPE.FRIEND+idUser), user?.title, user?.description, Constants.FRIEND_TYPE.FRIEND, Utils.getIdUser(context), "0"
                                                )
                                                if (res.isSuccessful) {
                                                    Utils.log(TAG+"hehe", "add friend success: ${res.body()?.status}")
                                                } else {
                                                    Utils.log(TAG+"hehe", "failed: ${res.errorBody()}")
                                                }
                                            } catch (e: Exception) {
                                                Utils.log(TAG+"hehe", "erro: ${e.message}")
                                            }
                                        }
                                    }
                            Utils.log(TAG, "size: ${listSocial.size}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${responseUser.errorBody()}")
                    }
                }


                val response = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.SOCIAL)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        listSocial.clear()
                        response.body()?.getAllList?.let {
                            for (x in it){
                                if (x.status=="0"){
                                    listSocial.add(x)
                                }
                            }
                            Utils.log(TAG, "size: ${listSocial.size}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response.errorBody()}")
                    }
                }

                val response2 = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.INFO)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response2: ${response2.body()}")
                    if (response2.isSuccessful) {
                        listInfo.clear()
                        response2.body()?.getAllList?.let {
                            for (x in it){
                                if (x.status=="0"){
                                    listInfo.add(x)
                                }
                            }
                            Utils.log(TAG, "size: ${listInfo.size}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response2.errorBody()}")
                    }
                }

                val response3 = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.BANK)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response3: ${response2.body()}")
                    if (response3.isSuccessful) {
                        listBank.clear()
                        response3.body()?.getAllList?.let {
                            for (x in it){
                                if (x.status=="0"){
                                    listBank.add(x)
                                }
                            }
                            Utils.log(TAG, "size: ${listBank.size}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response3.errorBody()}")
                    }
                }

                val response4 = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser, Constants.ITEM_TYPE.HEALTH)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response4: ${response4.body()}")
                    if (response4.isSuccessful) {
                        listHealth.clear()
                        response4.body()?.getAllList?.let {
                            for (x in it){
                                if (x.status=="0"){
                                    listHealth.add(x)
                                }
                            }
                            Utils.log(TAG, "size: ${listHealth.size}")
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response4.errorBody()}")
                    }
                }

            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }
}