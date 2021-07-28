package com.romainbechard.linxotest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Photo(
    @JsonProperty("albumId") val albumId: Int,
    @JsonProperty("id") val photoId: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("thumbnailUrl") val thumbnailUrl: String
)