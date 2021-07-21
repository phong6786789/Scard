package com.subi.scard.view.fragment.show_card

import androidx.databinding.ObservableField
import com.subi.scard.base.viewmodel.BaseViewModel

class ShowCardViewmodel: BaseViewModel() {
    val fullname = ObservableField("")
    val email = ObservableField("")
    val avatar = ObservableField("")

    fun load(idUser:String){

    }
}