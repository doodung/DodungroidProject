package com.example.firstsem.Home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstsem.R

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val subTitle: TextView = itemView.findViewById(R.id.subTitle)

    fun onBind(data: SampleDATA) {
        title.text = data.title
        subTitle.text = data.subTitle
    }
}