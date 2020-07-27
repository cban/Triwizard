package com.potter.triwizard.repository.impl

import com.potter.triwizard.data.House
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.HousesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class HouseRepositoryImp @Inject constructor(private var twizardApi: TwizardApi) :
    HousesRepository {

    override suspend fun getHouses() = twizardApi.getHouses()

    override suspend fun getHouseById(house_id: String) = twizardApi.getHouse(house_id)
}