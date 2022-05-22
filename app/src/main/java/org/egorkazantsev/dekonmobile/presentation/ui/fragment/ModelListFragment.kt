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
import org.egorkazantsev.dekonmobile.R
import org.egorkazantsev.dekonmobile.databinding.FragmentModelListBinding
import org.egorkazantsev.dekonmobile.presentation.ui.adapter.ModelListAdapter
import org.egorkazantsev.dekonmobile.presentation.viewmodel.ModelListViewModel

@AndroidEntryPoint
class ModelListFragment : Fragment(), ModelListAdapter.OnItemClickListener {

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

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_modelListFragment_to_modelFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            modelListRecyclerView.apply {
                adapter = viewModel.modelListLiveData.value?.let {
                    ModelListAdapter(it, this@ModelListFragment)
                }
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}