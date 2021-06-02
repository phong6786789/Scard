package com.subi.scard.di

import com.google.gson.GsonBuilder
import com.subi.scard.data.ApiService
import com.subi.scard.view.loginGG.LoginViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "http://192.168.1.27/scard/"

val applicationModule = module {
    single { provideRetrofit() }
    single { MyReposity(get()) }

    viewModel { LoginViewModel(get()) }
}
private fun provideRetrofit(): ApiService {
    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(ApiService::class.java)
}