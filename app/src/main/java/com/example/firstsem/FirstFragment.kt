package com.example.firstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {
    private lateinit var viewpagerAdapter:SampleViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        viewpagerAdapter= SampleViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments= listOf(
            InfoFragment(),
            OtherFragment()
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sample_tab_viewpager.adapter=viewpagerAdapter
        sample_tab.setupWithViewPager(sample_tab_viewpager)
        sample_tab.apply{
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
    }
}