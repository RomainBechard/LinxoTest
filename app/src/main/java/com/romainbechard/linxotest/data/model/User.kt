package com.romainbechard.linxotest.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val userId: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("email") val email: String,
    @JsonIgnoreProperties("address") val address: Any,
    @JsonIgnoreProperties("phone") val phone: String,
    @JsonIgnoreProperties("website") val website: String,
    @JsonIgnoreProperties("company") val company: Any
)