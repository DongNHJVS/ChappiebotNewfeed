package com.dongnh.chappiebotnewfeed.Model

data class Content (
    val href: String?,
    val preview_image: Image?,
    val duration: Int?,
    val text: String?,
    val markups: List<Markup>?
)
