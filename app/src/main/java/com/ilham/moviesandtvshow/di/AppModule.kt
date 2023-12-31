package com.ilham.moviesandtvshow.di

import android.content.Context
import androidx.room.Room
import com.ilham.moviesandtvshow.BuildConfig
import com.ilham.moviesandtvshow.data.source.local.room.Databases
import com.ilham.moviesandtvshow.data.source.local.room.Dao
import com.ilham.moviesandtvshow.data.source.network.retrofit.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): Databases = Room.databaseBuilder(
        context, Databases::class.java, "Netplix.db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(databases: Databases): Dao = databases.databaseObject()

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
        .create(ApiService::class.java)
}