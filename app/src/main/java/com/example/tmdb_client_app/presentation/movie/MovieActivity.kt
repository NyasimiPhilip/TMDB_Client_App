package com.example.tmdb_client_app.presentation.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tmdb_client_app.databinding.ActivityMovieBinding  // Make sure to import the correct binding class
import com.example.tmdb_client_app.R

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object using the generated binding class
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

    }
}
