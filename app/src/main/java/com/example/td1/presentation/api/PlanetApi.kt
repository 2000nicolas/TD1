package com.example.td1.presentation.api

import retrofit2.Call
import retrofit2.http.GET


interface PlanetApi{
    @GET("pokemon")
    fun getPlaneteList(): Call<PlanetResponse>
}