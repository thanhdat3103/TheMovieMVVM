package com.example.themoviemvvm.data.value_object

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val budget: Int,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long, // In case of Connection Problem
    val runtime: Int,
    val tagline: String,
    val status: String,
    @SerializedName("vote_average")
    val rating: String,
    val title: String
)