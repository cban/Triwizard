package com.potter.triwizard.repository

import com.potter.triwizard.data.Character
import com.potter.triwizard.data.House
import retrofit2.Response

interface StudentRepository {
    suspend  fun get(): Response<List<Character>>
    suspend fun getCharacterById(character_id: String): Response<Character>
}