package com.example.weatherapp.di

import com.example.weatherapp.network.NetworkApiInterface
import com.example.weatherapp.network.Retrofit
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideNetwork(retrofit: Retrofit): NetworkApiInterface {
       return retrofit.provideService(NetworkApiInterface::class.java)
    }
}
