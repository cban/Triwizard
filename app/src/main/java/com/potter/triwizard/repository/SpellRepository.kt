package com.potter.triwizard.repository

import com.potter.triwizard.data.Spell
import retrofit2.Response

interface SpellRepository {
    suspend fun getSpells(): Response<List<Spell>>
}