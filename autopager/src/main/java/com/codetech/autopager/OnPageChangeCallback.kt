package com.codetech.autopager

/**
 * Callback interface for observing page changes in the ViewPager.
 */
interface OnPageChangeCallback {
    /**
     * Called when a new page becomes selected.
     *
     * @param position The position of the newly selected page.
     */
    fun onPageSelected(position: Int)

    /**
     * Called when the scroll state changes.
     *
     * @param state The new scroll state. Possible values are:
     *              [ViewPager2.SCROLL_STATE_IDLE],
     *              [ViewPager2.SCROLL_STATE_DRAGGING],
     *              [ViewPager2.SCROLL_STATE_SETTLING].
     */
    fun onPageScrollStateChanged(state: Int)

    /**
     * Called when the current page is scrolled.
     *
     * @param position The position of the page being scrolled.
     * @param positionOffset The offset from the page's position.
     * @param positionOffsetPixels The pixel offset from the page's position.
     */
    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
}