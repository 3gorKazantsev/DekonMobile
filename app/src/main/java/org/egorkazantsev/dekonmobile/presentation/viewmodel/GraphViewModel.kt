package org.egorkazantsev.dekonmobile.presentation.viewmodel

import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns.*
import android.widget.Toast
import androidx.lifecycle.*
import com.github.mikephil.charting.data.Entry
import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.usecase.GetCriteriaByIdUC
import org.egorkazantsev.dekonmobile.domain.usecase.GetModelByIdUC
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

private const val FONT = "/assets/fonts/arialmt.ttf"

@HiltViewModel
class GraphViewModel @Inject constructor(
    application: Application,
    state: SavedStateHandle,
    private val getModelByIdUC: GetModelByIdUC,
    private val getCriteriaByIdUC: GetCriteriaByIdUC
) : AndroidViewModel(application) {

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

    // сохранение PDF файла
    fun createPDF(fileName: String, graphBmp: Bitmap) {
        val contentResolver = getApplication<Application?>().contentResolver

        val values = ContentValues()
        values.apply {
            put(DISPLAY_NAME, createProperName(fileName))
            put(MIME_TYPE, "application/pdf")
            put(RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Dekon")
        }

        val uri: Uri? = contentResolver
            .insert(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL), values)

        if (uri != null) {
            val outputStream = contentResolver.openOutputStream(uri)
            val document = Document()
            PdfWriter.getInstance(document, outputStream)
            document.apply {
                open()
                addAuthor("DekonMobile")
                inflatePDF(document, graphBmp)
                close()
            }

            Toast.makeText(getApplication(), "PDF created in Documents/Dekon", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // наполнение PDF файла
    private fun inflatePDF(document: Document, graphBmp: Bitmap) {
        val baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
        val fontTitle = Font(baseFont, 24f, Font.NORMAL)
        val fontSubtitle = Font(baseFont, 16f, Font.NORMAL)
        val fontMatrix = Font(baseFont, 14f, Font.BOLD)
        val fontCriteria = Font(baseFont, 14f, Font.NORMAL)

        val paraTitle = Paragraph("Матрица: ${model.value!!.name}\n" +
                "Критерий: ${criteria.value!!.name}\n\n", fontTitle)

        val tableDesc = Paragraph("Таблица элементов модели (матриц и критериев) и их значений\n\n", fontSubtitle)
        val table = PdfPTable(2)
        table.setWidths(floatArrayOf(230f, 20f))
        for (element in model.value!!.root.elements) {
            if (element is Matrix) {
                table.addCell(Phrase(element.name, fontMatrix))
                table.addCell(Phrase(element.value.toString(), fontMatrix))
            } else {
                table.addCell(Phrase(element.name, fontCriteria))
                table.addCell(Phrase(element.value.toString(), fontCriteria))
            }
        }

        val stream = ByteArrayOutputStream()
        graphBmp.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val image = Image.getInstance(stream.toByteArray())

        val documentWidth: Float =
            document.pageSize.width - document.leftMargin() - document.rightMargin()
        val documentHeight: Float =
            document.pageSize.height - document.topMargin() - document.bottomMargin()
        image.scaleToFit(documentWidth, documentHeight)

        document.apply {
            add(paraTitle)
            add(tableDesc)
            add(table)
            add(image)
        }
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
                add(
                    Entry(
                        i.toFloat(),
                        org.egorkazantsev.dekonmobile.data.storage.random().toFloat()
                    )
                )
            }
        }
    }
}