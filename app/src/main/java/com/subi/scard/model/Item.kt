package com.subi.scard.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    val status: String?,
    @SerializedName("item")
    var getAllList: List<Item>?
)

data class Item(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("idUser")
    val idUser: String?,
    @SerializedName("status")
    val status: String?
)