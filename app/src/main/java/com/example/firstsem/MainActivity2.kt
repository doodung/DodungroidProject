package com.example.firstsem

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.properties.Delegates

class MainActivity2 : AppCompatActivity() {
    private lateinit var viewPagerAdapter: SampleViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewPagerAdapter= SampleViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.fragments= listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        sample_viewpager.adapter=viewPagerAdapter

        /*
        sample_viewpager.addOnPageChangeListener(object : ViewPager){

        }
        */

        bottomNavigationView.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_brush->index=0
                R.id.menu_camera->index=1
                else -> index=2
            }
            sample_viewpager.currentItem=index
            true
        }
    }
}