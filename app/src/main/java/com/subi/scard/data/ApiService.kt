package com.subi.scard.data

import com.subi.scard.model.Status
import com.subi.scard.model.UserModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("user/checkUserById.php")
    @FormUrlEncoded
    suspend fun checkUser(
        @Field("idUser") idUser: String
    ): Response<Status>

    @GET("user/getUserById.php")
    suspend fun getUserById(
        @Query("idUser") idUser: String
    ): Response<UserModel>

    @GET("user/setStatus.php")
    suspend fun setStatus(
        @Query("idUser") idUser: String,
        @Query("status") status: String
    ): Response<String>

    @GET("item/insert.php")
    suspend fun insertItem(
        @Query("title") title: String,
        @Query("description") description: String,
        @Query("type") type: String,
        @Query("idUser") idUser: String,
        @Query("status") status: String
    ): Response<String>

    @GET("item/getAllByIdUser.php")
    suspend fun getAllItemByIdUser(
        @Query("idUser") idUser: String
    ): Response<MutableList<UserModel>>
}