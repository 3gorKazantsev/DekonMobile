package org.egorkazantsev.dekonmobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetAllModelsUseCase
import javax.inject.Inject

@HiltViewModel
class ModelListViewModel @Inject constructor(
    private val getAllModelsUseCase: GetAllModelsUseCase
): ViewModel() {

    private val _modelListLiveData = MutableLiveData<List<Model>>()
    val modelListLiveData: LiveData<List<Model>> = _modelListLiveData

    fun getAllModels() {
        val modelList = getAllModelsUseCase.execute()
        _modelListLiveData.value = modelList
    }
}