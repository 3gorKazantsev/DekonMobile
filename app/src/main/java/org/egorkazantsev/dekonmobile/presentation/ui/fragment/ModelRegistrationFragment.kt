package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.egorkazantsev.dekonmobile.R
import org.egorkazantsev.dekonmobile.databinding.FragmentModelRegistrationBinding
import java.text.SimpleDateFormat
import java.util.*

class ModelRegistrationFragment : Fragment() {

    private var _binding: FragmentModelRegistrationBinding? = null
    private val binding get() = _binding!!

    private val args: ModelRegistrationFragmentArgs by navArgs()

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelRegistrationBinding.inflate(inflater, container, false)

        // изменение заголовка экшн бара
        activity?.title = getString(R.string.model_registration)

        val date = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, day)
                updateText()
            }
        }

        // установка наименования и ФИО
        binding.apply {
            modelNameEditText.setText(args.modelName)
            fullNameEditText.setText(args.ownerName)
        }

        // слушатели
        binding.apply {
            birthDateEditText.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        return binding.root
    }

    private fun updateText() {
        val format = "MM.dd.yyyy"
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        binding.birthDateEditText.setText(dateFormat.format(calendar.time))
    }
}