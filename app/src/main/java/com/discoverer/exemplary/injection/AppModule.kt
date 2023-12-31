package com.discoverer.exemplary.injection

import android.content.Context
import com.discoverer.exemplary.BuildConfig
import com.discoverer.exemplary.api.ApiHelper
import com.discoverer.exemplary.api.ApiHelperImpl
import com.discoverer.exemplary.api.ApiService
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.model.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * appModule: a Koin module that contains various objects that are injected throughout the app.
 */
val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

/**
 * Method that creates a NetworkHelper object to be injected in any object that needs it.
 */
private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

/**
 * Method that provides an instance of the OkHttpClient to whoever needs it. It will add HTTP logging
 * when a DEBUG build is being made.
 */
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

/**
 * Method that provides a Retrofit object that uses Moshi in order to convert data from
 * JSON to Kotlin objects.
 */
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()
}

/**
 * Method that provides the class generated by Retrofit that implements the code to query the
 * web API we are currently using.
 */
private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

