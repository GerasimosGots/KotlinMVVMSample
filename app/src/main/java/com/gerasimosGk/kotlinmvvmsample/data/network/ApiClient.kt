package com.gerasimosGk.kotlinmvvmsample.data.network

import com.gerasimosGk.kotlinmvvmsample.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Gerasimos on 21/11/2021
 *
 * Single instance Class (Singleton)
 * This object is responsible for the network request
 * Used In Hilt Modules
 */
object ApiClient {

    // Primary Url for User List Data
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    // Secondary Url for the images
    const val SECONDARY_URL = "https://picsum.photos/id/"

    /**
     * Creates and Returns an Instance of Retrofit
     *
     * We provide tha Base Url and tha Gson and RxJava Factory so we can parse the received data
     * and use it as Stream
     */
    fun createPrimaryRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .client(buildOkhttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    /**
     * Creates and Returns an OkHttpClient so we can use it in the Retrofit instance
     *
     * We provide connect time out, read time out, headers and interceptors
     * based the case
     */
    private fun buildOkhttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)

        // Http Interceptor is an interceptor for login the network request
        // Only in Debug Variant
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }
}