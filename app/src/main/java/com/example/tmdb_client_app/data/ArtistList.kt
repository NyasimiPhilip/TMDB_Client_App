package com.example.tmdb_client_app.data


import com.google.gson.annotations.SerializedName

data class ArtistList(

    @SerializedName("results")
    val results: List<Artist>,
)