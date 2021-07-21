package com.subi.scard.service.network

import com.subi.scard.model.ItemModel
import com.subi.scard.model.Status
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ScardApi {
    //USER
    @POST("user/checkUserById.php")
    @FormUrlEncoded
    suspend fun checkUserById(
        @Field("idUser") idUser: String?
    ): Response<Status>

    //ITEM
    @GET("item/getAllItemByIdUser.php")
    suspend fun getAllItemByIdUser( @Query("idUser") idUser: String?): Response<ItemModel>

    @GET("item/getAllItemByIdUserAndType.php")
    suspend fun getAllItemByIdUserAndType(
        @Query("idUser") idUser: String?,
        @Query("type") type: String?
    ): Response<ItemModel>

    @GET("item/insertItem.php")
    suspend fun insertItem(
        @Query("id") id: String?,
        @Query("title") title: String?,
        @Query("description") description: String?,
        @Query("type") type: String?,
        @Query("idUser") idUser: String?,
        @Query("status") status: String?
    ): Response<ItemModel>

    @GET("item/editItemById.php")
    suspend fun editItemById(
        @Query("id") id: String?,
        @Query("title") title: String?,
        @Query("description") description: String?,
        @Query("type") type: String?,
        @Query("idUser") idUser: String?,
        @Query("status") status: String?
    ): Response<ItemModel>

    @GET("item/deleteItemById.php")
    suspend fun deleteItemById(
        @Query("id") id: String?
    ): Response<Status>
}