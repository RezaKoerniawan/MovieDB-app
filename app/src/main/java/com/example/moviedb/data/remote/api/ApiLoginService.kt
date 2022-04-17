package com.example.moviedb.data.remote.api

import com.example.moviedb.data.remote.request.LoginRequest
import com.example.moviedb.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiLoginService {

    @POST("login?&")
    suspend fun getLoginUser(@Body loginRequest: LoginRequest?): LoginResponse
}