package com.example.weatherapp.di

import com.example.weatherapp.network.NetworkApiInterface
import com.example.weatherapp.network.Retrofit
import com.example.weatherapp.network.RetrofitProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
 class RetrofitModule {

    @Provides
    fun provideNetwork(retrofit: Retrofit): NetworkApiInterface {
       return retrofit.provideRetrofit(NetworkApiInterface::class.java)
    }

}