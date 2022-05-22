package org.egorkazantsev.dekonmobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import org.egorkazantsev.dekonmobile.domain.usecase.GetAllModelsUC

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllModelsUseCase(modelRepository: ModelRepository): GetAllModelsUC {
        return GetAllModelsUC(modelRepository = modelRepository)
    }
}