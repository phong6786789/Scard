package com.subi.scard.model

import com.google.gson.annotations.SerializedName

data class Theme (

    @SerializedName("id")
    var id: String? = "",

    @SerializedName("idUser")
    var idUser: String? = "",

    @SerializedName("background")
    var background: String? = "",

    @SerializedName("font")
    var font: String? = "",

    @SerializedName("size")
    var size: String? = ""

)

data class ThemeRepo(
    @SerializedName("theme")
    val list: List<Theme>?
)