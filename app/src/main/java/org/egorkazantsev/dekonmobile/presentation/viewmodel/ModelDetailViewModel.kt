package org.egorkazantsev.dekonmobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetModelByIdUC
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ModelDetailViewModel @Inject constructor(
    private val getModelById: GetModelByIdUC,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _modelLiveData = MutableLiveData<Model>()
    val modelLiveData: LiveData<Model> = _modelLiveData

    init {
        // загружаем модель по ID полученному из SafeArgs
        state.get<UUID>("modelId")?.let { loadModelById(it) }
    }

    private fun loadModelById(id: UUID) {
        val model = getModelById.execute(id)
        _modelLiveData.value = model
    }
}