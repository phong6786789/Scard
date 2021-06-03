package com.subi.scard.di

import com.subi.scard.data.ApiService
import com.subi.scard.model.ItemModel

class MyReposity(private val api: ApiService) {
    //user
    suspend fun checkUser(idUser: String) = api.checkUser(idUser)
    suspend fun getUserById(idUser: String) = api.getUserById(idUser)
    suspend fun setStatus(idUser: String, status: String) = api.setStatus(idUser, status)


    //item
    suspend fun insertItem(item: ItemModel) =
        api.insertItem(item.title, item.description, item.type, item.idUser, item.status)
    suspend fun getAllItemById(idUser: String) = api.getAllItemByIdUser(idUser)
}