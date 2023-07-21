package com.example.themoviemvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.themoviemvvm.data.api.TheMovieDBInterface
import com.example.themoviemvvm.data.repository.MovieDetailsNetworkDataSource
import com.example.themoviemvvm.data.repository.NetworkState
import com.example.themoviemvvm.data.value_object.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}