package com.example.cvschallenge_abedur_chowdhury.di

import com.example.cvschallenge_abedur_chowdhury.data.remote.FlickrApi
import com.example.cvschallenge_abedur_chowdhury.data.repository.FlickrRepositoryImpl
import com.example.cvschallenge_abedur_chowdhury.domain.repository.FlickrRepository
import com.example.cvschallenge_abedur_chowdhury.domain.use_cases.search_images.SearchImagesUseCase
import com.example.cvschallenge_abedur_chowdhury.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlickrAppModules {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideFlickrApi(retrofit: Retrofit) : FlickrApi {
        return retrofit.create(FlickrApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlickrRepository(api: FlickrApi) : FlickrRepository {
        return FlickrRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchImagesUseCase(repository: FlickrRepository) =
        SearchImagesUseCase(repository)
}