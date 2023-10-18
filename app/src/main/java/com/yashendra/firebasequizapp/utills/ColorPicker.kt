package com.yashendra.firebasequizapp.utills

object ColorPicker {
    val colors= arrayOf(
        "#3EB650",
        "#FF0000",
        "#FFA500",
        "#FF00FF",
        "#0000FF",
        "#00FFFF",
        "#008000",
        "#800080",
        "#808000",
        "#FFC0CB",
        "#800000",
        "#FFFF00",
        "#00FF00",
        "#000080",
        "#008080",
        "#C0C0C0",
        "#808080",
        "#FFD700",
        "#FF7F50",
        "#FF6347")
    var colorIndex=0
    fun getColor():String{
        colorIndex=(colorIndex+1)% colors.size
        return colors[colorIndex]
    }
}