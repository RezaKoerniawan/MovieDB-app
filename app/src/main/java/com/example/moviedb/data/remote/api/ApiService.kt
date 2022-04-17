package com.example.moviedb.data.remote

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.api.ApiPath
import com.example.moviedb.data.remote.request.LoginRequest
import com.example.moviedb.data.remote.response.*
import retrofit2.http.*

interface ApiService {
    @GET(ApiPath.DISCOVER_MOVIE)
    suspend fun getDiscoverMovie(@QueryMap hashMap: HashMap<String, String> = HashMap()): GetMovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovie(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: String): GetCastAndCrewResponse

    @GET("3/movie/{movie_id}/images")
    suspend fun getMovieImages(@Path("movie_id") movieId: String): GetMovieImages

    @GET(ApiPath.DISCOVER_TV)
    suspend fun getDiscoverTv(@QueryMap hashMap: HashMap<String, String> = HashMap()): GetTvListResponse
}