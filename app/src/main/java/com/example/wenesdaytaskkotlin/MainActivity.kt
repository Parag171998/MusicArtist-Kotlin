package com.example.wenesdaytaskkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        var gridLayoutManager: GridLayoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        recyclerView!!.layoutManager = gridLayoutManager


        retrieveJson("jack+johnson")
    }
    fun retrieveJson(artist: String?) {

        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        var apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val artistSongsCall: Call<ArtistSongs?>? = apiInterface.getArtist(artist,"25")

        /*val artistSongsCall: Call<ArtistSongs> =
            ApiClient.getInstance().getApi().getArtist(artist, "25")

         */

        artistSongsCall?.enqueue(object : Callback<ArtistSongs?> {
            override fun onResponse(call: Call<ArtistSongs?>,response: Response<ArtistSongs?>) {

                val artistSongs = response.body()
                val resultList: MutableList<Result> =
                    ArrayList()
                resultList.addAll(artistSongs?.results!!)
                val artistAdapter =
                    ArtistAdapter(applicationContext, resultList)
                recyclerView!!.adapter = artistAdapter
            }

            override fun onFailure(call: Call<ArtistSongs?>,t: Throwable) {
            }
        })
    }
}
