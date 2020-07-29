package com.potter.triwizard.injection

import com.potter.triwizard.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideHouseRepository(impl: HouseRepositoryImpI): HouseRepository

    @Binds
    abstract fun provideSpellRepository(impl: SpellRepositoryImpl): SpellRepository

    @Binds
    abstract fun provideStudentRepository(impl: CharacterRepositoryImpl): CharacterRepository
}