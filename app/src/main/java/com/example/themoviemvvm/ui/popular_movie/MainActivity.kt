package com.example.themoviemvvm.ui.popular_movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviemvvm.R
import com.example.themoviemvvm.data.api.TheMovieDBClient
import com.example.themoviemvvm.data.api.TheMovieDBInterface
import com.example.themoviemvvm.data.repository.NetworkState
import com.example.themoviemvvm.data.repository.Status
import com.example.themoviemvvm.ui.single_movie_details.SingleMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    lateinit var movieRepository: MoviePagedListRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

        movieRepository = MoviePagedListRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)

        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            if (it != null) {
                txt_error_popular.visibility = if (viewModel.listIsEmpty() && it.status == Status.FAILED) View.VISIBLE else View.GONE
            }
            if (!viewModel.listIsEmpty()) {
                if (it != null) {
                    movieAdapter.setNetworkState(it)
                }
            }

        })
    }

    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(movieRepository) as T
            }
        })[MainActivityViewModel::class.java]
    }
}