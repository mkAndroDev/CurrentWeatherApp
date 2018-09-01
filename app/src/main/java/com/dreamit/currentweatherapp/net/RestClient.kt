package com.dreamit.currentweatherapp.net

import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun getRestClient(): WeatherService {
    val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .setLenient()
            .create()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val networkInterceptor = Interceptor { chain ->
        val request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder().addHeader("Authorization",
                Credentials.basic("", ""))
                .addHeader("Accept", HEADER_ACCEPT_TYPE)
                .build()

        chain.proceed(newRequest)
    }

    val okHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    return retrofit.create<WeatherService>(WeatherService::class.java)
}