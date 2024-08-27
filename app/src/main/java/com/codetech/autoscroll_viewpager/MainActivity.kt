package com.codetech.autoscroll_viewpager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codetech.autopager.OnPageChangeCallback
import com.codetech.autoscroll_viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mAdapter by lazy { ViewPagerAdapter() }
    private val mList by lazy { ArrayList<PagerModelClass>() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mList.add(PagerModelClass("Horse",R.drawable.io))
        mList.add(PagerModelClass("Cars",R.drawable.img))
        mList.add(PagerModelClass("Buildings",R.drawable.it))
        mList.add(PagerModelClass("Ocean",R.drawable.ith))


        mAdapter.submitList(mList)

        binding.viewPager.setAdapter(adapter = mAdapter)

        binding.viewPager.triggerAutoScroll(true)
        binding.viewPager.setScrollingDuration(3000)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback {
            override fun onPageSelected(position: Int) {
                Log.d("pageInfo", "onPageSelected: $position")
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

        })

    }
}