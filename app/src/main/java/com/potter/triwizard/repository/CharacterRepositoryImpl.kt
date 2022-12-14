package com.potter.triwizard.repository

import com.potter.triwizard.data.Character
import com.potter.triwizard.network.TwizardApi
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private var triwizardApi: TwizardApi) :
    CharacterRepository {

    override suspend fun getStudents(): Response<List<Character>> = triwizardApi.getCharacters()

    override suspend fun getCharacterById(character_id: String): Response<Character> =
        triwizardApi.getCharacterById(character_id)
}