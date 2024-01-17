package com.example.tmdb_client_app.data.model.tvShow


import com.example.tmdb_client_app.data.model.tvShow.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowList(

    @SerializedName("results")
    val tvShow: List<TvShow>,

    )