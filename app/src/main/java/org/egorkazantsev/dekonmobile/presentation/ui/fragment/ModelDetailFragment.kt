package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.egorkazantsev.dekonmobile.databinding.FragmentModelDetailBinding
import org.egorkazantsev.dekonmobile.presentation.ui.adapter.ModelDetailAdapter
import org.egorkazantsev.dekonmobile.presentation.viewmodel.ModelDetailViewModel
import java.util.*

@AndroidEntryPoint
class ModelDetailFragment : Fragment(), ModelDetailAdapter.OnCriteriaClickListener {

    private var _binding: FragmentModelDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ModelDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // настройка RecyclerView
        with(binding) {
            modelDetailRecyclerView.apply {
                adapter = viewModel.modelLiveData.value?.let {
                    ModelDetailAdapter(it, this@ModelDetailFragment)
                }
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCriteriaClick(id: UUID) {
        Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onEditButtonClick(id: UUID) {
        Toast.makeText(context, "edit btn", Toast.LENGTH_SHORT).show()
    }
}