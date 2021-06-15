package com.subi.scard.view.fragment.settings

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.model.CustomItem

class SettingsViewmodel: BaseViewModel() {
    var list_menu: ObservableList<CustomItem> = ObservableArrayList()

    init {
        list_menu.addAll(
            listOf(
                CustomItem(1, R.drawable.ic_baseline_api_24, "Tùy biến giao diện"),
                CustomItem(2, R.drawable.ic_baseline_supervised_user_circle_24, "Cài đặt tài khoản"),
                CustomItem(3, R.drawable.ic_baseline_add_to_home_screen_24, "Chỉnh sửa màn hình chính"),
                CustomItem(4, R.drawable.ic_info, "Hướng dẫn sử dụng"),
                CustomItem(5, R.drawable.ic_baseline_exit_to_app_24, "Đăng xuất")
            )
        )
    }
}