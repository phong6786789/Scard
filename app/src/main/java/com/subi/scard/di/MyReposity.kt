package com.subi.scard.di

import com.subi.scard.data.ApiService

class MyReposity(private val api: ApiService) {
    suspend fun checkUser(idUser: String) = api.checkUser(idUser)

}