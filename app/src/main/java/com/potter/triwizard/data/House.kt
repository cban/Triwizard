package com.potter.triwizard.data

import com.google.gson.annotations.SerializedName

data class House(

    val _id: String,
    @SerializedName("name")
    val name: String="",
    @SerializedName("mascot")
    val mascot: String="",
    @SerializedName("headOfHouse")
    val headOfHouse: String="",
    @SerializedName("houseGhost")
    val houseGhost: String="",
    @SerializedName("founder")
    val founder: String="",
    @SerializedName("_v")
    val _v: Int= 0,
    @SerializedName("school")
    val school: String="",
    @SerializedName("members")
    val members: List<String>  = listOf(),
    @SerializedName("values")
    val values: List<String> = listOf(),
    @SerializedName("colors")
    val colors: List<String> = listOf()
)