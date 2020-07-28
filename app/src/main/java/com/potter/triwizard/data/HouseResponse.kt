package com.potter.triwizard.data

data class HouseResponse(
    val _id: String,
    val name: String,
    val mascot: String,
    val headOfHouse: String,
    val houseGhost: String,
    val founder: String,
    val __v: Int,
    val school: String,
    val members: List<Member>,
    val values: List<String>,
    val colors: List<String>
)

data class Member(
    val _id: String,
    val name: String
)