package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.egorkazantsev.dekonmobile.R
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // слушатель для модели для RecyclerView
        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            configureRecyclerView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.model_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // формирование Action
        val model = viewModel.modelLiveData.value
        val action = ModelDetailFragmentDirections
            .actionModelFragmentToModelRegistrationFragment(
                model?.name ?: "",
                model?.owner?.fullName ?: ""
            )

        // обработка нажатий в меню
        when (item.itemId) {
            R.id.registerModel -> findNavController().navigate(action)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // нажатие на элемент списка
    override fun onCriteriaClick(id: UUID) {
        val model = viewModel.modelLiveData.value
        val action = ModelDetailFragmentDirections
            .actionModelFragmentToGraphFragment(id, model!!.id)
        findNavController().navigate(action)
    }

    // нажатие на кнопку редактирования
    override fun onEditButtonClick(id: UUID) {
        // todo диалоговое окно изменения значения критерия
        viewModel.setCriteriaValue(id, 11.1)
    }

    // настройка RecyclerView
    private fun configureRecyclerView() {
        with(binding) {
            modelDetailRecyclerView.apply {
                adapter = viewModel.modelLiveData.value?.let {
                    ModelDetailAdapter(it, this@ModelDetailFragment)
                }
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }
}