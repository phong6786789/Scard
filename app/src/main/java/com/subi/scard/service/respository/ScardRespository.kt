package com.subi.pokemonproject.data.respository

import com.subi.pokemonproject.data.network.ScardApi
import com.subi.scard.model.Status
import com.subi.scard.model.User
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class ScardRespository @Inject constructor(
    private val scardApi: ScardApi
) {
    suspend fun checkUserById(idUser: String): Response<Status> = scardApi.checkUserById(idUser)

    suspend fun getUserById(idUser: String): Observable<ArrayList<User>> =
        scardApi.getUserById(idUser)

    suspend fun setStatus(idUser: String): String = scardApi.setStatus(idUser)

}