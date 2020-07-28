package com.potter.triwizard.repository

import com.potter.triwizard.data.Character
import com.potter.triwizard.network.TwizardApi
import retrofit2.Response
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(private var twizardApi: TwizardApi) :
    StudentRepository {
    override suspend fun get(): Response<List<Character>> = twizardApi.getCharacters()

    override suspend fun getCharacterById(character_id: String): Response<Character> =
        twizardApi.getCharacterById(character_id)
}