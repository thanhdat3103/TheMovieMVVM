package com.example.themoviemvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.themoviemvvm.data.api.POST_PER_PAGE
import com.example.themoviemvvm.data.api.TheMovieDBInterface
import com.example.themoviemvvm.data.repository.MovieDataSource
import com.example.themoviemvvm.data.repository.MovieDataSourceFactory
import com.example.themoviemvvm.data.repository.NetworkState
import com.example.themoviemvvm.data.value_object.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState?> {
        val networkStateLiveData = MediatorLiveData<NetworkState?>()

        val moviesLiveDataSource = movieDataSourceFactory.moviesLiveDataSource

        // Add a data source change listener to moviesLiveDataSource
        networkStateLiveData.addSource(moviesLiveDataSource) { movieDataSource ->
            networkStateLiveData.value = movieDataSource?.networkState?.value
        }

        return networkStateLiveData
    }



}