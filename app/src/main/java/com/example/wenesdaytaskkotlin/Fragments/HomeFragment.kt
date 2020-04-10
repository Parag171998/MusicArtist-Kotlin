package com.example.wenesdaytaskkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wenesdaytaskkotlin.*
import com.example.wenesdaytaskkotlin.Adapters.ArtistAdapter
import com.example.wenesdaytaskkotlin.Models.ArtistSongs
import com.example.wenesdaytaskkotlin.Models.Result
import com.example.wenesdaytaskkotlin.Network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        var gridLayoutManager: GridLayoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)

        view.recycler_view_homeFragment!!.layoutManager = gridLayoutManager

        retrieveJson("jack+johnson")


        return view
    }

    fun retrieveJson(artist: String?) {

        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var apiInterface: ApiInterface = retrofit.create(
            ApiInterface::class.java)

        val artistSongsCall: Call<ArtistSongs?>? = apiInterface.getArtist(artist,"25")

        /*val artistSongsCall: Call<ArtistSongs> =
            ApiClient.getInstance().getApi().getArtist(artist, "25")

         */

        artistSongsCall?.enqueue(object : Callback<ArtistSongs?> {
            override fun onResponse(call: Call<ArtistSongs?>, response: Response<ArtistSongs?>) {

                val artistSongs = response.body()
                val resultList: MutableList<Result> =
                    ArrayList()
                resultList.addAll(artistSongs?.results!!)
                val artistAdapter =
                    ArtistAdapter(
                        context,
                        resultList
                    )
                recycler_view_homeFragment!!.adapter = artistAdapter
            }

            override fun onFailure(call: Call<ArtistSongs?>, t: Throwable) {
            }
        })
    }

}
