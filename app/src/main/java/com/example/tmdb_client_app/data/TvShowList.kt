package com.example.tmdb_client_app.data


import com.google.gson.annotations.SerializedName

data class TvShowList(

    @SerializedName("results")
    val results: List<TvShow>,

)