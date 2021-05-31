package com.subi.scard.data

import com.subi.scard.model.Status
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("user/checkUserById.php")
    @FormUrlEncoded
    suspend fun checkUser(
        @Field("idUser") idUser: String
    ): Response<Status>
}