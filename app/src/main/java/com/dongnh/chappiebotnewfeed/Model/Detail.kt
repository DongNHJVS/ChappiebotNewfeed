package com.dongnh.chappiebotnewfeed.Model

data class Detail (
    val document_id: String?,
    val title: String?,
    val description: String?,
    val published_date: String?,
    val origin_url: String?,
    val publisher: Publisher?,
    val template_type: String?,
    val sections: List<Section>?
)