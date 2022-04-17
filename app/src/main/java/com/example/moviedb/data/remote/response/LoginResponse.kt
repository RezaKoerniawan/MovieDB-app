package com.example.moviedb.data.remote.response


import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "userName") val userName: String? = null,
    @Json(name = "avatar") val avatar: String? = null,
    @Json(name = "id") val id: String? = null,
    @Json(name = "password") val password: String? = null
) : BaseResponse()