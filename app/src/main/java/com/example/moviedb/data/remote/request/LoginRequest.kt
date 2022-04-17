package com.example.moviedb.data.remote.request

import com.squareup.moshi.Json

class LoginRequest(
    @Json(name = "userName") var userName: String?,
    @Json(name = "password") var password: String?
)