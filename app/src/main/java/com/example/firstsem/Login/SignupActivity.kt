package com.example.firstsem.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.firstsem.MyServiceImpl
import com.example.firstsem.R
import com.example.firstsem.RequestSignupData
import com.example.firstsem.ResponseSignupData
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    private fun showError(error : ResponseBody?)
    {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString ("message"),Toast. LENGTH_SHORT).show()
    }

    private fun init(){
            val email = et_signup_id.text.toString()
            val password = et_signup_pw.text.toString()
            val name = et_signup_name.text.toString()

            val call : Call<ResponseSignupData> = MyServiceImpl.service.postSignup(
                RequestSignupData(
                    email= email,
                    password= password,
                    userName= name
                )
            )
            call.enqueue(object:Callback<ResponseSignupData>{
                override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
                override fun onResponse(
                    call: Call<ResponseSignupData>,
                    response: Response<ResponseSignupData>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignupActivity, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                        if (response.body()!!.success) {
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    else
                    {
                        showError(response.errorBody())
                    }
                }
            })
        }

    private fun checkedEmptyEditText(editText: EditText): Boolean {
        return !TextUtils.isEmpty(editText.text.toString())
    }
}
