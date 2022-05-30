package org.egorkazantsev.dekonmobile.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import androidx.lifecycle.*
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetCriteriaByIdUC
import org.egorkazantsev.dekonmobile.domain.usecase.GetModelByIdUC
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class GraphViewModel @Inject constructor(
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

    // формирование pfd документа
    fun createPDF(name: String, graphBmp: Bitmap) {
        val paint = Paint()
        val text = Paint().apply {
            textSize = 15f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            color = Color.BLACK
            textAlign = Paint.Align.LEFT
        }
        val pdfDoc = PdfDocument()

        val page1Info = PdfDocument.PageInfo.Builder(graphBmp.width, graphBmp.height, 1).create()
        val page1 = pdfDoc.startPage(page1Info)
        val canvas = page1.canvas
        canvas.drawBitmap(graphBmp, 1f, 1f, paint)
        pdfDoc.finishPage(page1)

        // todo сделать вторую страницу, где список всех элементов

        val storageState = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED != storageState)
            return

        val fileName = createProperName(name)
        val file = File(Environment.getExternalStorageDirectory().path + "/Download/$fileName")

        try {
            pdfDoc.writeTo(FileOutputStream(file))
            Log.d("DDDebug", "success")
        } catch (e: IOException) {
            e.printStackTrace()
//                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }

        pdfDoc.close()
    }

    private fun createProperName(name: String): String {
        return "${name.replace(Regex("[^0-9a-zA-Zа-яёА-ЯЁ]+"), "_")}.pdf"
    }

    private fun loadModelById(id: UUID) {
        val model = getModelByIdUC.execute(id)
        _modelLiveData.value = model
    }

    private fun loadCriteriaById(modelId: UUID, criteriaId: UUID) {
        val criteria = getCriteriaByIdUC.execute(modelId, criteriaId)
        _criteriaLiveData.value = criteria
    }

    private fun generateDataSet() {
        _dataSetLiveData.value = arrayListOf()
        _dataSetLiveData.value?.apply {
            for (i in 0..9) {
                add(Entry(i.toFloat(), org.egorkazantsev.dekonmobile.data.storage.random().toFloat()))
            }
        }
    }
}