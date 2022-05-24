package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import org.egorkazantsev.dekonmobile.R
import org.egorkazantsev.dekonmobile.databinding.FragmentGraphBinding
import org.egorkazantsev.dekonmobile.presentation.viewmodel.GraphViewModel

@AndroidEntryPoint
class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GraphViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // настройка графика
        configureGraph()

        // слушатели для текущего значения критерия и матрицы
        with(viewModel) {
            currentCriteriaValue.observe(viewLifecycleOwner) {
                setCurrentValue()
            }
            currentMatrixValue.observe(viewLifecycleOwner) {
                setCurrentValue()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // настройка графика
    private fun configureGraph() {
        val lineDataSet = LineDataSet(viewModel.dataSet.value, getString(R.string.matrix_criteria))
        lineDataSet.apply {
            lineWidth = 3f
            highlightLineWidth = 1.5f
        }
        val lineData = LineData(lineDataSet)
        with(binding.graphLineChart) {
            data = lineData
            setVisibleXRange(0f, 10f)
            setScaleEnabled(false)
            description.isEnabled = false
            // слушатель для выбранного элемента
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    viewModel.setCurrentValue(h?.x, h?.y)
                }
                override fun onNothingSelected() {
                    viewModel.setCurrentValue(null, null)
                }
            })
        }
    }

    // установка значений в TextView текущего значения критерия и матрицы
    private fun setCurrentValue() {
        binding.apply {
            criteriaValueTextView.text = viewModel.currentCriteriaValue.value.toString()
            matrixValueTextView.text = viewModel.currentMatrixValue.value.toString()
        }
    }
}