package com.example.firstsem.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firstsem.R
import com.example.firstsem.Home.RecyclerActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        autoLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1001){
            if(resultCode== Activity.RESULT_OK)
            {
                et_id.setText(data!!.getStringExtra("id").toString())
                et_pw.setText(data!!.getStringExtra("pass").toString())
            }
        }
    }

    fun autoLogin(){
        val preference = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference!!.edit()
        if(preference.getString("id", "").isNullOrBlank()||preference.getString("pw", "").isNullOrBlank())
        {
            init()
        }
        else
        {
            Toast.makeText(this, "자동로그인 되었습니다", Toast.LENGTH_SHORT).show()
            val gotologinintent = Intent(this, RecyclerActivity::class.java)
            startActivity(gotologinintent)
            finish()
        }
    }

    private fun init() {
        buttonMapping()
    }

    private fun buttonMapping() {
        btn_login.setOnClickListener(this)
        btn_gosignup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login -> {
                checkboxLogin()
                val gotologinintent = Intent(this, RecyclerActivity::class.java)
                startActivity(gotologinintent)
                finish()
            }

            R.id.btn_gosignup -> {
                val gosignupintent = Intent(this, SignupActivity::class.java)
                startActivityForResult(gosignupintent,1001)
            }
        }
    }

    fun checkboxLogin() {
        val preference = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference!!.edit()
        if (cb_autologin.isChecked) {
            editor.putString("id", et_id.text.toString())
            editor.putString("pw", et_pw.text.toString())
            editor.clear()
            editor.apply()
            Toast.makeText(this, "다음부터 자동으로 로그인됩니다.", Toast.LENGTH_LONG).show()
        }
        else
        {
            editor.remove("id")
            editor.remove("pw")
            editor.clear()
            editor.apply()
        }
    }
}