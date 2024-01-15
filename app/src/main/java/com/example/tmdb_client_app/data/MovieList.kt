package com.example.tmdb_client_app.data


import com.example.tmdb_client_app.data.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("results")
    val results: List<Movie>,

    )