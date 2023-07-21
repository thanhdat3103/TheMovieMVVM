package com.example.themoviemvvm.data.api

import com.example.themoviemvvm.data.value_object.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBInterface {

    @GET("movie/{movie_id}")  //upcoming , popular
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

}