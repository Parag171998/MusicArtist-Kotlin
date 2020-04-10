package com.example.wenesdaytaskkotlin.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wenesdaytaskkotlin.Adapters.ArtistAdapter
import com.example.wenesdaytaskkotlin.Models.ArtistSongs
import com.example.wenesdaytaskkotlin.Models.Result
import com.example.wenesdaytaskkotlin.Network.ApiClient
import com.example.wenesdaytaskkotlin.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)

        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        var gridLayoutManager: GridLayoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)

        view.recycler_view_homeFragment!!.layoutManager = gridLayoutManager

        //changed name retrieveJson to getArtists_Data
        getArtists_Data("jack+johnson")


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.my_menu,menu)
        var menuItem = menu.findItem(R.id.search_bar)
        var searchView = menuItem.actionView as SearchView

        searchView.queryHint = "Search Artists!"

        searchView.setOnQueryTextListener(object : OnQueryTextListener{

            override fun onQueryTextChange(newText: String): Boolean {
                getArtists_Data(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return true
            }

        })
         super.onCreateOptionsMenu(menu, inflater)
    }


    fun getArtists_Data(artist: String) {

        val artistSongsCall: Call<ArtistSongs?>? =
            ApiClient.getInstance()?.getApi()?.getArtist(artist, "25")


        artistSongsCall?.enqueue(object : Callback<ArtistSongs?> {
            override fun onResponse(call: Call<ArtistSongs?>, response: Response<ArtistSongs?>) {

                val artistSongs = response.body()
                val resultList: MutableList<Result> =
                    ArrayList()
                resultList.addAll(artistSongs?.results!!)
                val artistAdapter =
                    ArtistAdapter(
                        context!!,
                        resultList
                    )
                recycler_view_homeFragment!!.adapter = artistAdapter
            }

            override fun onFailure(call: Call<ArtistSongs?>, t: Throwable) {
            }
        })
    }

}

