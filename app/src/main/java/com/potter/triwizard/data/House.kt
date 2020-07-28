package com.potter.triwizard.data

import com.google.gson.annotations.SerializedName

data class House(

    val _id: String,
    val name: String="",
    val mascot: String="",
    val headOfHouse: String="",
    val houseGhost: String="",
    val founder: String="",
    val _v: Int= 0,
    val school: String="",
    val members: List<String>  = listOf(),
    val values: List<String> = listOf(),
    val colors: List<String> = listOf()
)