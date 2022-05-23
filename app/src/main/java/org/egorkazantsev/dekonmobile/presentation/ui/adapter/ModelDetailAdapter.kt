package org.egorkazantsev.dekonmobile.presentation.ui.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.egorkazantsev.dekonmobile.databinding.CriteriaItemBinding
import org.egorkazantsev.dekonmobile.databinding.MatrixItemBinding
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model

private const val MATRIX_VIEW_TYPE = 0
private const val CRITERIA_VIEW_TYPE = 1

class ModelDetailAdapter(
    private val model: Model
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val elements = model.root.elements

    inner class MatrixViewHolder(
        val binding: MatrixItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // биндинг всех UI компонентов
        fun bind(matrix: Matrix) {
            with(binding) {
                // текста для TextView
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
    ) : RecyclerView.ViewHolder(binding.root) {
        // биндинг всех UI компонентов
        fun bind(criteria: Criteria) {
            with(binding) {
                // текста для TextView
                criteriaNameTextView.text = criteria.name
                criteriaValueTextView.text = criteria.value.toString()

                // цвет по уровню для карточки
                val color = makeColor(criteria.level)
                elementColorCardView.setCardBackgroundColor(color)

                // TODO повесить слушательно, но реализацию сделать выше
                editValueImageButton.setOnClickListener(null)
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
}