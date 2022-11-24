package com.momen.nytimes

import android.app.Application
import com.momen.nytimes.di.ApplicationComponent
import com.momen.nytimes.di.DaggerApplicationComponent

class NewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}