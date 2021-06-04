package com.subi.pokemonproject.data.network

import com.subi.scard.model.Status
import com.subi.scard.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ScardApi {

    //USER
    @POST("user/checkUserById2.php")
    @FormUrlEncoded
    suspend  fun checkUserById(
        @Field("idUser") idUser: String?
    ): Response<Status>

    @GET("user/getUserById.php")
    suspend  fun getUserById(
        @Query("idUser") idUser: String?
    ): Observable<ArrayList<User>>

    @POST("user/setStatus.php")
    suspend fun setStatus(
        @Query("idUser") idUser: String?
    ): String
}