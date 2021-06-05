package com.subi.scard.model

import com.google.gson.annotations.SerializedName

class Theme {
    @SerializedName("idUser")
    var idUser: String? = ""

    @SerializedName("background")
    var background: String? = ""

    @SerializedName("font")
    var font: String? = ""

    @SerializedName("size")
    var size: String? = ""
}