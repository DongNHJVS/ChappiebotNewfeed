package com.dongnh.chappiebotnewfeed.Model

data class Feed(
    val document_id: String?,
    val title: String?,
    val description: String?,
    val published_date: String?,
    val content_type: String?,
    val origin_url: String?,
    val publisher: Publisher?,
    val avatar: Image?,
    val images: List<Image>?,
    val content: Content?
)