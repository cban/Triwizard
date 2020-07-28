package com.potter.triwizard.repository

import com.potter.triwizard.data.Character
import com.potter.triwizard.data.House
import retrofit2.Response

interface CharacterRepository {
    suspend  fun getStudents(): Response<List<Character>>
    suspend fun getCharacterById(character_id: String): Response<Character>
}