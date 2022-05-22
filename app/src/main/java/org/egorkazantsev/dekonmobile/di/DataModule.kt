package org.egorkazantsev.dekonmobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.egorkazantsev.dekonmobile.data.repository.ModelRepositoryImpl
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideModelRepository(): ModelRepository {
        return ModelRepositoryImpl()
    }
}