package com.example.tmdb_client_app.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb_client_app.databinding.ActivityMovieBinding  // Make sure to import the correct binding class
import com.example.tmdb_client_app.R
import com.example.tmdb_client_app.data.model.movie.Movie
import com.example.tmdb_client_app.presentation.dI.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the binding object using the generated binding class
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMoviesSubComponent()
            .inject(this)


        movieViewModel = ViewModelProvider(this, factory )[MovieViewModel::class.java]

        initRecyclerView()
        /**
         * val responseLiveData: LiveData<List<Movie>?> = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            Log.i("MYTAG", it.toString())
        })
         */
    }
    private fun initRecyclerView(){
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            if(it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            }else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext,"No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}
