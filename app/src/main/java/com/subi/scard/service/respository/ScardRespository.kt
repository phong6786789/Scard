package com.subi.pokemonproject.data.respository

import com.subi.scard.model.ItemModel
import com.subi.scard.model.Status
import com.subi.scard.model.Theme
import com.subi.scard.model.ThemeRepo
import com.subi.scard.service.network.ScardApi
import retrofit2.Response
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
        id: String?,
        title: String?,
        description: String?,
        type: String?,
        idUser: String?,
        status: String?
    )
            : Response<ItemModel> =
        scardApi.insertItem(id, title, description, type, idUser, status)

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

    suspend fun checkThemeByIdUser(
        idUser: String?,
        type: String?
    )
            : Response<ThemeRepo> =
        scardApi.checkThemeByIdUser(idUser, type)

    suspend fun editThemeByIdUserAndType(
        idUser: String?,
        background: String?,
        type: String?
    )
            : Response<Status> =
        scardApi.editThemeByIdUserAndType(idUser, background, type)
}