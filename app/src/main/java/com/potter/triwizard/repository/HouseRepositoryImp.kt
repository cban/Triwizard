package com.potter.triwizard.repository

import com.potter.triwizard.network.TwizardApi
import javax.inject.Inject

class HouseRepositoryImp @Inject constructor(private var twizardApi: TwizardApi) :
    HouseRepository {

    override suspend fun getHouses() = twizardApi.getHouses()

    override suspend fun getHouseById(house_id: String) = twizardApi.getHouse(house_id)
}