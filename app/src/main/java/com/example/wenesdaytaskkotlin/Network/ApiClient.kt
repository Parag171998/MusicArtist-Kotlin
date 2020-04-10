package com.example.wenesdaytaskkotlin.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var BASE_URL: String = "https://itunes.apple.com/"
    private var apiClient2: ApiClient? = null
    private var retrofit: Retrofit? = null

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getInstance(): ApiClient? {
        if (apiClient2 == null) {
            apiClient2 = ApiClient
        }
        return apiClient2
    }

    fun getApi(): ApiInterface? {
        return retrofit?.create(ApiInterface::class.java)
    }
}