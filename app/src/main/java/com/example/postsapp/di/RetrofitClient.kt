package com.example.postsapp.di

import com.example.postsapp.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitClient {

    @Singleton
    @Provides
    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit) : ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }
}