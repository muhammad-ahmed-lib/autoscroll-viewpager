package com.codetech.autopager


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

// Extension function for custom logging
fun String.println() {
    Log.d("autoScrollViewPager", this)
}

/**
 * @author Muhammad Ahmed
 * @created 8/27/2024
 *
 * Custom ViewPager2 implementation with automatic sliding functionality.
 *
 * @property viewPager The ViewPager2 instance used for displaying pages.
 * @property scrollingDuration Duration between auto-slide transitions in milliseconds.
 * @property isAutoSliding Boolean indicating whether auto-slide is active.
 * @property updateHandler Handler for managing auto-slide scheduling.
 * @property autoSlideRunnable Runnable for executing the auto-slide operation.
 * @property mPageCallback Callback interface for observing page changes.
 *
 * @constructor Creates an instance of AutoSliderViewPager with optional attributes.
 * @param context The context to use.
 * @param attrs Optional attribute set to apply.
 * @param defStyleAttr Optional default style attribute.
 */
class AutoScrollViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val viewPager: ViewPager2 = ViewPager2(context)
    private var scrollingDuration: Long = 3000 // Default interval of 3 seconds
    private var isAutoSliding = false
    private val updateHandler = Handler(Looper.getMainLooper())
    private val autoSlideRunnable = object : Runnable {
        override fun run() {
            if (isAutoSliding) {
                val itemCount = viewPager.adapter?.itemCount ?: 0
                if (itemCount > 0) {
                    viewPager.currentItem = (viewPager.currentItem + 1) % itemCount
                }
                updateHandler.postDelayed(this, scrollingDuration)
            }
        }
    }
    private var mPageCallback: OnPageChangeCallback? = null

    init {
        // Add the ViewPager2 to the layout with full size
        addView(viewPager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    /**
     * Starts or stops auto-scrolling based on the provided flag.
     *
     * @param isAutoScroll Boolean indicating whether to enable or disable auto-scrolling.
     */
    fun triggerAutoScroll(isAutoScroll: Boolean) {
        if (isAutoScroll) {
            startAutoSlide()
        } else {
            stopAutoSlide()
        }
        "triggerAutoScroll: $isAutoScroll".println()
    }

    /**
     * Starts auto-scrolling if it's not already running.
     */
    private fun startAutoSlide() {
        if (!isAutoSliding) {
            isAutoSliding = true
            updateHandler.postDelayed(autoSlideRunnable, scrollingDuration)
            "startAutoSlide: Auto-slide started".println()
        }
    }

    /**
     * Stops auto-scrolling if it's currently running.
     */
    private fun stopAutoSlide() {
        if (isAutoSliding) {
            isAutoSliding = false
            updateHandler.removeCallbacks(autoSlideRunnable)
            "stopAutoSlide: Auto-slide stopped".println()
        }
    }

    /**
     * Sets the duration for auto-scrolling and restarts the auto-slide if it is active.
     *
     * @param duration The duration between auto-slide transitions in milliseconds.
     */
    fun setScrollingDuration(duration: Long) {
        scrollingDuration = duration
        if (isAutoSliding) {
            stopAutoSlide()
            startAutoSlide()
        }
        "setScrollingDuration: Duration set to $duration".println()
    }

    /**
     * Sets the adapter for the ViewPager and updates the auto-slide logic.
     *
     * @param adapter The RecyclerView.Adapter to be set.
     */
    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        viewPager.adapter = adapter
        // Update auto sliding logic to start/stop based on the new adapter's item count
        if (isAutoSliding) {
            stopAutoSlide()
            startAutoSlide()
        }
        "setAdapter: Adapter set with item count: ${adapter.itemCount}".println()
    }

    /**
     * Stops auto-scrolling when the view is detached from the window.
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAutoSlide() // Ensures sliding stops when the view is detached
        "onDetachedFromWindow: View detached, auto-slide stopped".println()
    }

    /**
     * Registers a callback to observe page changes.
     *
     * @param listener The callback interface for page change events. Null values are ignored.
     */
    fun registerOnPageChangeCallback(listener: OnPageChangeCallback?) {
        if (listener == null) {
            "registerOnPageChangeCallback: Listener is null".println()
            return
        }
        mPageCallback = listener
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPageCallback?.onPageSelected(position)
                "observeCurrentPage: Current page $position".println()
            }

            override fun onPageScrollStateChanged(state: Int) {
                mPageCallback?.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                mPageCallback?.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    /**
     * Gets the current page index of the ViewPager.
     *
     * @return The index of the currently displayed page.
     */
    fun getCurrentPage(): Int {
        return viewPager.currentItem
    }
}
