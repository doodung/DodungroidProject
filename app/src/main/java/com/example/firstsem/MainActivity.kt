package com.example.firstsem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.firstsem.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val LiveData = MutableLiveData(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this))
        //binding은 view를 한단계 감싸서 참조함. 그래서 .root 해야함
        setContentView(binding.root)
        //binding.text="안녕하세요"
        //binding . 을 하면 그 뷰안에있는 것들 참조할 수 있음.
        //binding 장점 ? id랑 헷갈일 릴 없음. 형 safety하게 작업 가능하다.

        var str = "hhhihih"
        binding.lifecycleOwner=this //자동으로 관찰, 해제까지 해줌

        //val str2 = MutableLiveData("ehea")
        //binding.text=str



        binding.btnLogin.setOnClickListener{
            //binding.text="안녕히가세요"
            str="byebye"
            //binding text가 바뀌지 않음. why???

            LiveData.value= LiveData.value!!+1
            Toast.makeText(this,"로그인에 성공하였습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,BaseActivity::class.java)
            startActivity(intent)
        }
        et_id.text.toString()



    }
    private fun observeLiveData(){
        LiveData.observe(this){
            binding.btnLogin.text=it.toString()
            //mainactivity와 livedata연동. 이게 databinding이다...xml은 부가적인것
            Log.e("Tag",it.toString())
        }
    }
}