package com.example.wenesdaytaskkotlin.Network

import com.example.wenesdaytaskkotlin.Models.ArtistSongs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search")
    fun getArtist(
        @Query("term") term: String?,
        @Query("limit") limit: String?
    ): Call<ArtistSongs?>?
}