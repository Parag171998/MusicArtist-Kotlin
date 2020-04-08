package com.example.wenesdaytaskkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArtistAdapter(

    context: Context?,
    resultList: List<Result>

) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    var layoutInflater: LayoutInflater
    var resultList: List<Result>

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

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var artistImg: ImageView
        var name: TextView

        init {
            artistImg = itemView.findViewById(R.id.img)
            name = itemView.findViewById(R.id.name)
        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.resultList = resultList
    }
}