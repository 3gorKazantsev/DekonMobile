package org.egorkazantsev.dekonmobile.presentation.ui.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.egorkazantsev.dekonmobile.databinding.CriteriaItemBinding
import org.egorkazantsev.dekonmobile.databinding.MatrixItemBinding
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import java.util.*

private const val MATRIX_VIEW_TYPE = 0
private const val CRITERIA_VIEW_TYPE = 1

class ModelDetailAdapter(
    private val model: Model,
    private val onCriteriaClickListener: OnCriteriaClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val elements = model.root.elements

    inner class MatrixViewHolder(
        val binding: MatrixItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // биндинг всех UI компонентов
        fun bind(matrix: Matrix) {
            with(binding) {

                // размер и стиль текста для главной матрицы
                if (matrix.level == 0) {
                    matrixNameTextView.apply {
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                        typeface = Typeface.DEFAULT_BOLD
                    }
                    matrixValueTextView.apply {
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                        typeface = Typeface.DEFAULT_BOLD
                    }
                }

                // текста для TextView
                matrixNameTextView.text = matrix.name
                matrixValueTextView.text = matrix.value.toString()

                // цвет по уровню для карточки
                val color = makeColor(matrix.level)
                elementColorCardView.setCardBackgroundColor(color)
            }
        }
    }

    inner class CriteriaViewHolder(
        val binding: CriteriaItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        // биндинг всех UI компонентов
        fun bind(criteria: Criteria) {
            with(binding) {
                // текста для TextView
                criteriaNameTextView.text = criteria.name
                criteriaValueTextView.text = criteria.value.toString()

                // цвет по уровню для карточки
                val color = makeColor(criteria.level)
                elementColorCardView.setCardBackgroundColor(color)

                // клик по ImageButton
                editValueImageButton.setOnClickListener(this@CriteriaViewHolder)
                // клик по элементу списка
                root.setOnClickListener(this@CriteriaViewHolder)
            }
        }

        // обработка кликов
        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            val id = elements[position].id
            if (position != RecyclerView.NO_POSITION)
                if (v != null) {
                    when (v.id) {
                        binding.root.id ->
                            onCriteriaClickListener.onCriteriaClick(id)
                        binding.editValueImageButton.id ->
                            onCriteriaClickListener.onEditButtonClick(id)
                    }
                }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (elements[position] is Matrix)
            MATRIX_VIEW_TYPE
        else
            CRITERIA_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MATRIX_VIEW_TYPE)
            MatrixViewHolder(
                MatrixItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        else
            CriteriaViewHolder(
                CriteriaItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MATRIX_VIEW_TYPE)
            (holder as MatrixViewHolder).bind(elements[position] as Matrix)
        else
            (holder as CriteriaViewHolder).bind(elements[position])
    }

    override fun getItemCount() = elements.size

    private fun makeColor(level: Int): Int {
        val size = model.maxLevel
        val minColor = 0
        val maxColor = 240
        val jump = (maxColor - minColor) / (size * 1.0)
        return Color.HSVToColor(
            floatArrayOf(
                (minColor + (jump * level)).toFloat(), 0.6f, 1.0f
            )
        )
    }

    interface OnCriteriaClickListener {
        fun onCriteriaClick(id: UUID)
        fun onEditButtonClick(id: UUID)
    }
}