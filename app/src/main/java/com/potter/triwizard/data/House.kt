package com.potter.triwizard.data


data class House(
    val _id: String,
    val name: String,
    val mascot: String,
    val headOfHouse: String,
    val houseGhost: String,
    val founder: String,
    val __v: Int,
    val school: String,
    val members: List<String>,
    val values: List<String>,
    val colors: List<String>
)