package com.subi.scard.view.fragment.settings

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem
import com.subi.scard.view.MainActivity

class SettingsViewmodel: BaseViewModel() {
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()
    var name = ObservableField("")
    var image = ObservableField("")
    var id = ObservableField("")
    init {
        list_menu.addAll(
            listOf(
                CustomItem(1, R.drawable.ic_theme, "Tùy biến giao diện"),
                CustomItem(3, R.drawable.ic_phone, "Chỉnh sửa màn hình chính"),
                CustomItem(4, R.drawable.ic_guide, "Hướng dẫn sử dụng"),
                CustomItem(5, R.drawable.ic_logout, "Đăng xuất")
            )
        )
        name.set(MainActivity.user)
        image.set(MainActivity.avatar)
        id.set("Id: "+ MainActivity.idUser)
    }
}