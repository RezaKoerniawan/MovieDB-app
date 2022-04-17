package com.example.moviedb.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "username")
    val username: String,
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "password")
    val password: String
)