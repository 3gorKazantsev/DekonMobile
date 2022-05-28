package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.Toast
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

        // изменение заголовка экшн бара
        activity?.title = viewModel.criteria.value?.name

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.graph_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.currentCriteriaValue.value != null && viewModel.currentMatrixValue.value != null) {
            when (item.itemId) {
                R.id.saveGraph -> {
                    // формирование pdf файла и его сохранение
                    val bmp = createBmp(binding.graphLineChart)
                    viewModel.model.value?.let { viewModel.createPDF(it.name, bmp) }
                }
            }
        } else
            Toast.makeText(context, R.string.no_values_selected, Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createBmp(v: View): Bitmap {
        val bmp = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        v.draw(canvas)
        return bmp
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
            if (viewModel.currentCriteriaValue.value != null)
                criteriaValueTextView.text = viewModel.currentCriteriaValue.value.toString()
            else
                criteriaValueTextView.text = "-"
            if (viewModel.currentMatrixValue.value != null)
                matrixValueTextView.text = viewModel.currentMatrixValue.value.toString()
            else
                matrixValueTextView.text = "-"
        }
    }
}