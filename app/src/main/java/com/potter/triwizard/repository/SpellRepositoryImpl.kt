package com.potter.triwizard.repository

import com.potter.triwizard.data.Spell
import com.potter.triwizard.network.TwizardApi
import retrofit2.Response
import javax.inject.Inject

class SpellRepositoryImpl @Inject constructor(private var twizardApi: TwizardApi) :
    SpellRepository {
    override suspend fun getSpells(): Response<List<Spell>> = twizardApi.getSpells()
}