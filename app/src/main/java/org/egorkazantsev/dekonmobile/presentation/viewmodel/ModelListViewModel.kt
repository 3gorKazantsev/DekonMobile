package org.egorkazantsev.dekonmobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetAllModelsUC
import javax.inject.Inject

@HiltViewModel
class ModelListViewModel @Inject constructor(
    private val getAllModelsUC: GetAllModelsUC
): ViewModel() {

    private val _modelListLiveData = MutableLiveData<List<Model>>()
    val modelListLiveData: LiveData<List<Model>> = _modelListLiveData

    init {
        getAllModels()
    }

    fun getAllModels() {
        val modelList = getAllModelsUC.execute()
        _modelListLiveData.value = modelList
    }
}