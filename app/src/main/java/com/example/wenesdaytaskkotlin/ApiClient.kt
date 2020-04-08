package com.example.wenesdaytaskkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{

    private var BASE_URL: String? = "https://itunes.apple.com/"
    private var apiClient: ApiClient? = null
    private var retrofit: Retrofit? = null

    constructor() {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Synchronized
    fun getInstance(): ApiClient? {
        if (apiClient == null) {
            apiClient = ApiClient()
        }
        return apiClient
    }

    fun getApi(): ApiInterface? {
        return retrofit!!.create(ApiInterface::class.java)
    }
}