package com.example.themoviemvvm.data.api

import com.example.themoviemvvm.data.value_object.MovieDetails
import com.example.themoviemvvm.data.value_object.MoviePopularResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("move/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MoviePopularResponse>
    @GET("movie/{movie_id}")  //upcoming , popular
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

}