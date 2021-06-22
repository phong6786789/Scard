package com.subi.pokemonproject.data.respository

import com.subi.pokemonproject.data.network.ScardApi
import com.subi.scard.model.Item
import com.subi.scard.model.ItemModel
import com.subi.scard.model.Status
import com.subi.scard.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class ScardRespository @Inject constructor(
    private val scardApi: ScardApi
) {
    //User
    suspend fun checkUserById(idUser: String): Response<Status> = scardApi.checkUserById(idUser)

    //Item
    suspend fun getAllItemByIdUser(idUser: String)
            : Response<ItemModel> = scardApi.getAllItemByIdUser(idUser)

    suspend fun getAllItemByIdUserAndType(idUser: String, type: String)
            : Response<ItemModel> = scardApi.getAllItemByIdUserAndType(idUser, type)

    suspend fun insertItem(
        title: String?,
        description: String?,
        type: String?,
        idUser: String?,
        status: String?
    )
            : Response<ItemModel> =
        scardApi.insertItem(title, description, type, idUser, status)

    suspend fun editItemById(
        id: String?,
        title: String?,
        description: String?,
        type: String?,
        idUser: String?,
        status: String?
    )
            : Response<ItemModel> =
        scardApi.editItemById(id, title, description, type, idUser, status)

    suspend fun deleteItemById(id: String)
            : Response<Status> = scardApi.deleteItemById(id)
}