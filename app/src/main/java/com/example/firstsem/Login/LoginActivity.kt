package com.example.firstsem.Login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firstsem.*
import com.example.firstsem.Home.MainActivity2
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    @SuppressLint("CommitPrefEdits")
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
            val gotologinintent = Intent(this, MainActivity2::class.java)
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
                val call : Call<ResponseSigninData> = MyServiceImpl.service.postSignin(
                    RequestSigninData(
                        email= et_id.text.toString(),
                        password= et_pw.text.toString(),
                    )
                )
                call.enqueue(object: Callback<ResponseSigninData> {
                    override fun onFailure(call: Call<ResponseSigninData>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                    override fun onResponse(
                        call: Call<ResponseSigninData>,
                        response: Response<ResponseSigninData>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.success) {
                                val gotologinintent = Intent(this@LoginActivity, MainActivity2::class.java)
                                Toast.makeText(this@LoginActivity, "로그인 완료!", Toast.LENGTH_SHORT).show()
                                startActivity(gotologinintent)
                                finish()
                            }
                        }
                        else
                        {
                        }
                    }
                })
            }

            R.id.btn_gosignup -> {
                val gosignupintent = Intent(this, SignupActivity::class.java)
                startActivityForResult(gosignupintent,1001)
                finish()
            }
        }
    }

    private fun checkboxLogin() {
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