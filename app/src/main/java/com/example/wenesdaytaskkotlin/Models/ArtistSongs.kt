package com.example.wenesdaytaskkotlin.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArtistSongs {
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null


}