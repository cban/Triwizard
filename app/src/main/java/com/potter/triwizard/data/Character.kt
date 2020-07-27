package com.potter.triwizard.data

data class Character(
    val _id: String,
    val name: String,
    val role: String,
    val house: String,
    val school: String,
    val __v: Int,
    val ministryOfMagic: Boolean,
    val orderOfThePhoenix: Boolean,
    val dumbledoresArmy: Boolean,
    val deathEater: Boolean,
    val bloodStatus: String,
    val species: String
)