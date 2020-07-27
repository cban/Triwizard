package com.potter.triwizard.network

import com.potter.triwizard.data.Character
import com.potter.triwizard.data.CharacterResponse
import com.potter.triwizard.data.House
import com.potter.triwizard.data.Spell
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TwizardApi {

    @GET("characters")
    suspend  fun getCharacters(): Response<CharacterResponse>

    @GET("characters/:character_id")
    suspend fun getStudent(@Path("character_id") accountId: String): Response<Character>

    @GET("houses")
   suspend fun getHouses(): Response<List<House>>

    @GET("houses/:house_id")
    suspend fun getHouse(@Path("houses_id") houseId: String): Response<House>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>
}