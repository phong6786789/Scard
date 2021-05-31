package com.subi.scard.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@StartApplication)
            modules(listOf(applicationModule))
        }
    }
}