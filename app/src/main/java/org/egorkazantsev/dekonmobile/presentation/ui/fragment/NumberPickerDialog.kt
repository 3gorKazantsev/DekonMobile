package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import org.egorkazantsev.dekonmobile.R
import org.egorkazantsev.dekonmobile.databinding.NumberPickerDialogBinding
import java.util.*

class NumberPickerDialog(
    private val id: UUID,
    private val value: Double,
    private val onSaveClickListener: OnSaveClickListener
) : DialogFragment() {

    private var _binding: NumberPickerDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = NumberPickerDialogBinding.inflate(layoutInflater)

        // значения для NumberPicker'ов
        with(binding) {
            integerNumberPicker.apply {
                minValue = 0
                maxValue = 10
                value = makeInteger(this@NumberPickerDialog.value)
            }
            decimalNumberPicker.apply {
                minValue = 0
                maxValue = 9
                value = makeDecimal(this@NumberPickerDialog.value)
            }
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
            .setTitle(R.string.edit_value)
            .setPositiveButton(R.string.save) { _, _ ->
                val value = makeDouble(
                    binding.integerNumberPicker.value,
                    binding.decimalNumberPicker.value
                )
                onSaveClickListener.onSaveClick(id, value)
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun makeDouble(integer: Int, decimal: Int): Double {
        return integer + (decimal.toDouble() / 10)
    }

    private fun makeInteger(double: Double): Int {
        val doubleStr = double.toString()
        val index = doubleStr.indexOf(".")
        return doubleStr.substring(0, index).toInt()
    }

    private fun makeDecimal(double: Double): Int {
        val doubleStr = double.toString()
        val index = doubleStr.indexOf(".")
        return doubleStr.substring(index + 1).toInt()
    }
}

interface OnSaveClickListener {
    fun onSaveClick(id: UUID, value: Double)
}