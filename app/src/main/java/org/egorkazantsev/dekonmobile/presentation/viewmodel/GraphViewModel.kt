package org.egorkazantsev.dekonmobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetCriteriaByIdUC
import org.egorkazantsev.dekonmobile.domain.usecase.GetModelByIdUC
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class GraphViewModel @Inject constructor (
    private val getModelByIdUC: GetModelByIdUC,
    private val getCriteriaByIdUC: GetCriteriaByIdUC,
    state: SavedStateHandle
) : ViewModel() {

    private val _modelLiveData = MutableLiveData<Model>()
    val model: LiveData<Model> = _modelLiveData

    private val _criteriaLiveData = MutableLiveData<Criteria>()
    val criteria: LiveData<Criteria> = _criteriaLiveData

    private val _dataSetLiveData = MutableLiveData<ArrayList<Entry>>()
    val dataSet: LiveData<ArrayList<Entry>> = _dataSetLiveData

    private val _currentCriteriaValueLiveData = MutableLiveData<Float?>()
    val currentCriteriaValue: LiveData<Float?> = _currentCriteriaValueLiveData

    private val _currentMatrixValueLiveData = MutableLiveData<Float?>()
    val currentMatrixValue: LiveData<Float?> = _currentMatrixValueLiveData

    init {
        // загружаем модель и критерий
        val modelId = state.get<UUID>("modelId")
        val criteriaId = state.get<UUID>("criteriaId")
        if (modelId != null && criteriaId != null) {
            loadModelById(modelId)
            loadCriteriaById(modelId, criteriaId)
        }

        // установка дата сета для графика
        generateDataSet()

        // установка начальных значений
        setCurrentValue(null, null)
    }

    // установка начальных значений
    fun setCurrentValue(criteria: Float?, matrix: Float?) {
        _currentCriteriaValueLiveData.value = criteria
        _currentMatrixValueLiveData.value = matrix
    }

    private fun loadModelById(id: UUID) {
        val model = getModelByIdUC.execute(id)
        _modelLiveData.value = model
    }

    private fun loadCriteriaById(modelId: UUID, criteriaId: UUID) {
        getCriteriaByIdUC.execute(modelId, criteriaId)
    }

    private fun generateDataSet() {
        _dataSetLiveData.value = arrayListOf()
        _dataSetLiveData.value?.apply {
            add(Entry(1f,6f))
            add(Entry(2f,3f))
            add(Entry(3f,1f))
            add(Entry(4f,3f))
            add(Entry(5f,5f))
            add(Entry(6f,6f))
        }
    }
}