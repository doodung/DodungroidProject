package com.example.firstsem

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log.d
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firstsem.Login.LoginActivity
import kotlinx.android.synthetic.main.fragment_third.*
import kotlinx.android.synthetic.main.fragment_third.view.*

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference!!.edit()
        val v = inflater.inflate(R.layout.fragment_third, container, false)


        v.btn_logout.setOnClickListener {
            val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.Theme_AppCompat_DayNight_Dialog))
            builder.setTitle("로그아웃하시겠습니까?")
            builder.setMessage(" ")
            builder.setPositiveButton("확인") {_,_->

                editor.remove("id")
                editor.remove("pw")
                editor.clear()
                editor.apply()

                Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            //builder.setNeutralButton() 맨 왼쪽버튼
            builder.setNegativeButton("취소") {_,_ ->
            }
            builder.show()
        }
        return v
    }
}