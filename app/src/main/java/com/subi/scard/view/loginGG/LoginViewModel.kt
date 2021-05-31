package com.subi.scard.view.loginGG

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.di.MyReposity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val res: MyReposity): BaseViewModel() {
    val status = MutableLiveData<String?>()

    fun checkUser(idUser: String){
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(res.checkUser(idUser).body()?.status)
        }
    }
}