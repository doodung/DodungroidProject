package com.example.firstsem.Home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.firstsem.*
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
            RecyclerFragment(),
            ThirdFragment()
        )
        sample_viewpager.adapter=viewPagerAdapter

        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener{
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_camera ->index=2
                R.id.menu_brush ->index=1
                else -> index=0
            }
            sample_viewpager.currentItem=index
            true
        }

        fab.setOnClickListener {
            val bottomSheet = BottomDialog()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }







    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
}