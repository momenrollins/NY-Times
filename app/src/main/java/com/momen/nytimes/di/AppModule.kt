package com.momen.nytimes.di

import com.momen.nytimes.api.NYTimesApi
import com.momen.nytimes.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideNYTimesApi(retrofit: Retrofit): NYTimesApi = retrofit.create(NYTimesApi::class.java)
}