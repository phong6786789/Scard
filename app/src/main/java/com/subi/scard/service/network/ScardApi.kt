package com.subi.pokemonproject.data.network

import com.subi.scard.model.ItemModel
import com.subi.scard.model.Status
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ScardApi {
    //USER
    @POST("user/checkUserById2.php")
    @FormUrlEncoded
    suspend fun checkUserById(
        @Field("idUser") idUser: String?
    ): Response<Status>

    //ITEM
    @POST("item/getAllByIdUser.php")
    @FormUrlEncoded
    suspend fun getAllItemByIdUser( @Field("idUser") idUser: String?): Response<ItemModel>
}