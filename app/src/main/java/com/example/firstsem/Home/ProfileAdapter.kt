package com.example.firstsem.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstsem.R

class ProfileAdapter(var context : Context): RecyclerView.Adapter<ProfileViewHolder>() {
    var data= mutableListOf<SampleDATA>()

    interface ItemClick
    {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
        if(itemClick != null)
        {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int =data.size
}