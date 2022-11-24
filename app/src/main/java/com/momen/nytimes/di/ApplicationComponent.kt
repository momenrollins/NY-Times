package com.momen.nytimes.di

import com.momen.nytimes.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(mainFragment: MainFragment)
}