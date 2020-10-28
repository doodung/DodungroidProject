package com.example.firstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*

class Recycler : AppCompatActivity() {
    private  lateinit var profileAdapter:ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        profileAdapter=ProfileAdapter(this)

        main_rcv.adapter=profileAdapter
        main_rcv.layoutManager=LinearLayoutManager(this)

        profileAdapter.data= mutableListOf(
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정"),
            SampleDATA("이름","이수정")
        )
        profileAdapter.notifyDataSetChanged()
    }
}