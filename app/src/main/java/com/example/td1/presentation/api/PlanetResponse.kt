package com.example.td1.presentation.api

import com.example.td1.presentation.list.Planete

data class PlanetResponse (
    val count:Int ,
    val next:String,
    val results: List<Planete>
)