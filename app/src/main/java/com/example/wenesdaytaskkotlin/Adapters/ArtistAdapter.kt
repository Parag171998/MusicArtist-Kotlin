package com.example.wenesdaytaskkotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wenesdaytaskkotlin.R
import com.example.wenesdaytaskkotlin.Models.Result
import kotlinx.android.synthetic.main.custom_grid_layout.view.*

class ArtistAdapter(context: Context?, resultList: List<Result>) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    var layoutInflater: LayoutInflater
    var resultList: List<Result>

    init {
        layoutInflater = LayoutInflater.from(context)
        this.resultList = resultList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = layoutInflater.inflate(R.layout.custom_grid_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(layoutInflater.context).load(resultList[position].artworkUrl30)
            .into(holder.artistImg)

        holder.name.text = resultList[position].artistName
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var artistImg: ImageView = itemView.image_view_artists
        var name: TextView = itemView.text_view_name

    }


}