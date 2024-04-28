package com.g59939.mobg6.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object UserService {
    private const val baseURL = "https://dnsrivnxleeqdtbyhftv.supabase.co/"
    val UserClient : UserHttpClient

    init {
        // create a converter JSON -> Kotlin
        val jsonConverter = MoshiConverterFactory.create()
        // create a Retrofit builder
        val retrofitBuilder : Retrofit.Builder = Retrofit.Builder().addConverterFactory(jsonConverter).baseUrl(baseURL)
        // create a Retrofit instance
        val retrofit : Retrofit = retrofitBuilder.build()
        // create our client
        UserClient = retrofit.create(UserHttpClient::class.java)
    }
}