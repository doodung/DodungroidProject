package com.example.firstsem

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val context: Context): RecyclerView.Adapter<ProfileViewHolder>() {
    var data= mutableListOf<SampleDATA>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false)

        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int =data.size
}