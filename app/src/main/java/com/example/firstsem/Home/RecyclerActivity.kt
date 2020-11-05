package com.example.firstsem.Home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstsem.R
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    private  lateinit var profileAdapter: ProfileAdapter

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val preference = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference!!.edit()

        /*
        btn_logout.setOnClickListener {
            editor.remove("id")
            editor.remove("pw")
            editor.clear()
            editor.apply()

            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        */

        profileAdapter= ProfileAdapter(this)

        profileAdapter.itemClick=object : ProfileAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra("title", profileAdapter.data[position].title)
                intent.putExtra("subtitle", profileAdapter.data[position].subTitle)
                startActivity(intent)
            }
        }

        main_rcv.adapter=profileAdapter
        main_rcv.layoutManager=LinearLayoutManager(this)
        main_rcv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        profileAdapter.data= mutableListOf(
            SampleDATA("수정", "공주"),
            SampleDATA("유진", "왕자"),
            SampleDATA("수진", "오토마타"),
            SampleDATA("영훈", "서팟장"),
            SampleDATA("민주", "디팟장"),
            SampleDATA("승호", "바보"),
            SampleDATA("동관", "헐트킬러"),
            SampleDATA("준엽", "구준엽")
        )
        profileAdapter.notifyDataSetChanged()
    }
}