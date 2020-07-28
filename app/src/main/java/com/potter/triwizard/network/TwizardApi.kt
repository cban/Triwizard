package com.potter.triwizard.network

import com.potter.triwizard.data.*
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwizardApi {

    @GET("characters")
    suspend  fun getCharacters(): Response<List<Character>>

    @GET("characters/{character_id}")
    suspend fun getCharacterById(@Path("character_id") accountId: String): Response<Character>

    @GET("houses")
   suspend fun getHouses(): Response<List<House>>

    @GET("houses/{id}")
    suspend fun getHouse(@Path("id") houseId: String): Response<List<HouseResponse>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>
}