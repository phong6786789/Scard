package com.subi.pokemonproject.data.network

import com.google.gson.GsonBuilder
import com.subi.pokemonproject.data.respository.ScardRespository
import com.subi.scard.service.network.ScardApi
import com.subi.scard.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BaseNetwork {

    private val BASE_URL = Constants.BASE_URL

    private val CONNECT_TIMEOUT = 10L
    private val READ_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 10L

    fun providerHttpClient() = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    fun providerRetrofit(url: String? = BASE_URL) = Retrofit.Builder()
        .baseUrl(url!!)
        .client(providerHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun scardApi() = providerRetrofit().create(ScardApi::class.java)

    fun getInstance() = ScardRespository(scardApi())
}