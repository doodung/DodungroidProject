package com.example.firstsem.Home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstsem.R
import kotlinx.android.synthetic.main.fragment_recycler.view.*

class RecyclerFragment : Fragment() {
    lateinit var profileAdapter: ProfileAdapter

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference!!.edit()
        val v = inflater.inflate(R.layout.fragment_recycler, container, false)

        profileAdapter= ProfileAdapter(context as MainActivity2)

        profileAdapter.itemClick=object :ProfileAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("title", profileAdapter.data[position].title)
                intent.putExtra("subtitle", profileAdapter.data[position].subTitle)
                startActivity(intent)
                //activity?.finish()
            }
        }

        v.main_rcv.adapter=profileAdapter
        v.main_rcv.layoutManager= LinearLayoutManager(context)
        v.main_rcv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

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
        return v
    }
}