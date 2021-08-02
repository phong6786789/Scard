package com.subi.scard.view.fragment.settings.list.theme_card

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem

class ThemeCardViewmodel: BaseViewModel() {
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()

    init {
        list_menu.addAll(
            listOf(
                CustomItem(1, R.drawable.ic_theme, "Giao diện thẻ card QR"),
                CustomItem(2, R.drawable.ic_themexanh, "Giao diện item MXH"),
                CustomItem(3, R.drawable.ic_themetim, "Màu sắc hệ thống app"),
                CustomItem(4, R.drawable.ic_themehong, "Di chuyển các nút")
            )
        )
    }
}