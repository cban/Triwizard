package com.potter.triwizard.repository

import com.potter.triwizard.data.House
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response

interface HousesRepository {
    suspend  fun getHouses(): Response<List<House>>
    suspend fun getHouseById(house_id: String): Response<House>
}