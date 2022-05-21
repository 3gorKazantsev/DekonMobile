package org.egorkazantsev.dekonmobile.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import org.egorkazantsev.dekonmobile.domain.usecase.GetAllModelsUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllModelsUseCase(modelRepository: ModelRepository): GetAllModelsUseCase {
        return GetAllModelsUseCase(modelRepository = modelRepository)
    }
}