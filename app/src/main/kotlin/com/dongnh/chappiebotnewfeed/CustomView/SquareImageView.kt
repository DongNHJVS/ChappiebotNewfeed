package com.dongnh.chappiebotnewfeed.CustomView

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class SquareImageView(context: Context) : AppCompatImageView(context) {

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()) //Snap to width
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun performClick(): Boolean {
        return true
    }
}