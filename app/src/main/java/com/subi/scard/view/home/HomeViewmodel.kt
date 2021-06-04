package com.subi.scard.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.di.MyReposity
import com.subi.scard.model.ItemModel
import com.subi.scard.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewmodel: BaseViewModel() {
    val user = MutableLiveData<UserModel?>()
    val statusSetStatus = MutableLiveData<String?>()
    val statusInsertItem = MutableLiveData<String?>()
    val dataUser = MutableLiveData<MutableList<UserModel>?>()

//    fun getUserById(idUser: String){
//        viewModelScope.launch {
//            user.postValue(res.getUserById(idUser).body())
//        }
//    }
//
//    fun setStatus(idUser: String, status: String){
//        viewModelScope.launch (Dispatchers.IO){
//            statusSetStatus.postValue(res.setStatus(idUser, status).body())
//        }
//    }
//
//    fun insertItem(item: ItemModel){
//        viewModelScope.launch (Dispatchers.IO){
//            statusInsertItem.postValue(res.insertItem(item).body())
//        }
//    }
//
//    fun getAllItemById(idUser: String){
//        viewModelScope.launch (Dispatchers.IO){
//            dataUser.postValue(res.getAllItemById(idUser).body())
//        }
//    }


}