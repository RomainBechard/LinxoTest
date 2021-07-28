package com.romainbechard.linxotest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Album(
    @JsonProperty("userId") val userId: Int,
    @JsonProperty("id") val albumId: Int,
    @JsonProperty("title") val title: String
)
