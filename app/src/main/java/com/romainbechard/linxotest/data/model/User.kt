package com.romainbechard.linxotest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val userId: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("username") val username: String
)