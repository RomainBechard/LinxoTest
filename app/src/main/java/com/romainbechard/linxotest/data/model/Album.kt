package com.romainbechard.linxotest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Album(
    @JsonProperty("userId") val userId: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("title") val title: String
)
