package com.subi.pokemonproject.data.respository

import com.subi.pokemonproject.data.network.ScardApi
import com.subi.scard.model.Item
import com.subi.scard.model.ItemModel
import com.subi.scard.model.Status
import com.subi.scard.model.User
import io.reactivex.Observable
import retrofit2.Call
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
}