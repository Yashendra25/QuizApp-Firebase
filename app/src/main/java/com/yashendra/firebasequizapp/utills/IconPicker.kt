package com.yashendra.firebasequizapp.utills

import com.yashendra.firebasequizapp.R

object IconPicker {
    var icons= arrayOf(
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
        R.drawable.icon6,
        R.drawable.icon7,
        R.drawable.icon8,
        R.drawable.icon9,
        R.drawable.icon10
    )
    var iconIndex=0
    fun getIcon():Int{
        iconIndex=(iconIndex+1)% icons.size
        return icons[iconIndex]
    }
}