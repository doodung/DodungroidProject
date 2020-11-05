package com.example.firstsem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {
    var code=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val fragment1=FirstFragment()
        val fragment2=SecondFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,fragment1).commit()

        btn_change.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when(code)
            {
                1->{
                    transaction.replace(R.id.fragment_container,fragment2)
                    code=2
                }
                2->{
                    transaction.replace(R.id.fragment_container,fragment1)
                    code=1
                }
            }
            transaction.commit()
        }
    }
}