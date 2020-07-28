package com.potter.triwizard.repository

import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import retrofit2.Response

interface HouseRepository {
    suspend  fun getHouses(): Response<List<House>>
    suspend fun getHouseById(house_id: String): Response<List<HouseResponse>>
}