package com.example.themoviemvvm.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themoviemvvm.data.repository.NetworkState
import com.example.themoviemvvm.data.value_object.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository : MovieDetailsRepository, movieId: Int) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val movieDetails : LiveData<MovieDetails> by lazy { movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId) }
    val networkState : LiveData<NetworkState> by lazy { movieRepository.getMovieDetailsNetworkState()}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}