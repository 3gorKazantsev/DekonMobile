package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.egorkazantsev.dekonmobile.databinding.FragmentModelListBinding
import org.egorkazantsev.dekonmobile.presentation.ui.adapter.ModelListAdapter
import org.egorkazantsev.dekonmobile.presentation.viewmodel.ModelListViewModel
import java.util.*

@AndroidEntryPoint
class ModelListFragment : Fragment(), ModelListAdapter.OnModelClickListener {

    private var _binding: FragmentModelListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ModelListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // настройка RecyclerView
        with(binding) {
            modelListRecyclerView.apply {
                adapter = viewModel.modelListLiveData.value?.let {
                    ModelListAdapter(it, this@ModelListFragment)
                }
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    // клик по элементу в RecyclerView
    override fun onModelClick(id: UUID) {
        val action = ModelListFragmentDirections
            .actionModelListFragmentToModelFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}