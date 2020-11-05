package com.example.firstsem.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.firstsem.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.lang.Boolean.TRUE

class SignupActivity : AppCompatActivity() {
    private val editText : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signupok.setOnClickListener {
            init()
        }
    }

    private fun init(){
        if (checkedEmptyEditText(et_signup_id)&&checkedEmptyEditText(et_signup_pw)&&checkedEmptyEditText(et_signup_name)
        ==TRUE)
        {
            Toast.makeText(this, "환영합니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("id", et_signup_id.text.toString())
            intent.putExtra("pass", et_signup_pw.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        else
        {
            Toast.makeText(this, "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkedEmptyEditText(editText: EditText): Boolean {
        return !TextUtils.isEmpty(editText.text.toString())
    }
}