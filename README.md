AutoScrollViewPager

AutoScrollViewPager is a custom Android library that extends the functionality of ViewPager2 by adding automatic sliding capabilities. It's designed to be easy to integrate into any Android project, providing a smooth and customizable auto-scroll experience for your ViewPager2.

#sample

https://github.com/user-attachments/assets/64122fcb-e271-4ae7-902b-c9535c0f3374

Features

Automatic Sliding: Automatically slides through the pages at a customizable interval.

Customizable Duration: Set the duration between auto-slide transitions.

Page Change Callback: Register a callback to observe page changes.

Easy Integration: Simple and straightforward integration with your existing ViewPager2 setup.

Installation

Gradle

To use AutoScrollViewPager in your project, add the following to your settings.gradle file to ensure you have the required repositories:

gradle

Copy code


dependencyResolutionManagement {
    
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
   
    repositories {
    
        mavenCentral()
        
        maven { url 'https://jitpack.io' }
    
    }

}


Then, add the library dependency in your build.gradle file:

gradle

Copy code

dependencies {
   
    implementation 'com.github.muhammad-ahmed-lib:autoscroll-viewpager:v1.0'

}

Usage

1. Add AutoScrollViewPager to your layout

Include AutoScrollViewPager in your XML layout file:

xml
Copy code

<com.codetech.autopager.AutoScrollViewPager
   
    android:id="@+id/viewPager"
    
    android:layout_width="match_parent"
    
    android:layout_height="match_parent"
   
    tools:context=".MainActivity"
    />
2. Set up AutoScrollViewPager in your Activity or Fragment

In your MainActivity or Fragment, initialize and configure AutoScrollViewPager:

kotlin
Copy code

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy { ViewPagerAdapter() }
    private val mList by lazy { ArrayList<PagerModelClass>() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Add items to the list
        mList.add(PagerModelClass("Horse", R.drawable.io))
        mList.add(PagerModelClass("Cars", R.drawable.img))
        mList.add(PagerModelClass("Buildings", R.drawable.it))
        mList.add(PagerModelClass("Ocean", R.drawable.ith))

        // Set up the adapter with the list
        mAdapter.submitList(mList)

        // Set the adapter to the AutoScrollViewPager
        binding.viewPager.setAdapter(adapter = mAdapter)

        // Enable auto-scrolling
        binding.viewPager.triggerAutoScroll(true)

        // Set the duration for auto-scrolling (in milliseconds)
        binding.viewPager.setScrollingDuration(3000)

        // Register a page change callback to observe page changes
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback {
            override fun onPageSelected(position: Int) {
                Log.d("pageInfo", "onPageSelected: $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Handle page scroll state changes if needed
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Handle page scroll if needed
            }
        })
    }
}

3. Customize AutoScrollViewPager

Enable/Disable Auto-Scroll:

kotlin
Copy code

binding.viewPager.triggerAutoScroll(true) // Enable
binding.viewPager.triggerAutoScroll(false) // Disable
Set Scrolling Duration:

kotlin
Copy code

binding.viewPager.setScrollingDuration(3000) // Set duration to 3 seconds

Observe Page Changes:

kotlin
Copy code

binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback {
    override fun onPageSelected(position: Int) {
        Log.d("pageInfo", "onPageSelected: $position")
    }
})

License

AutoScrollViewPager is released under the MIT License. See the LICENSE file for details.

Author

Developed by Muhammad Ahmed. For any queries, contact me at ahmed03160636141@gmail.com.
