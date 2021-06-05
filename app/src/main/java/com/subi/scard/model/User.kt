package com.subi.scard.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("idUser")
    var idUser: String? = ""

    @SerializedName("status")
    var status: String? = ""
}