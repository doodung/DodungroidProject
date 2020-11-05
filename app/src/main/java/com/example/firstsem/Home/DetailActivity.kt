package com.example.firstsem.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstsem.R
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val extras = intent.extras

        tv_title.text=extras!!.getString("title")
        tv_subtitle.text= extras.getString("subtitle")
    }
}