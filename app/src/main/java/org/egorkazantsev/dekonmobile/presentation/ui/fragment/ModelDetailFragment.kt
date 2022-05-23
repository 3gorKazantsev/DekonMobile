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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.model_detail_menu, menu)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // формирование Action
        val model = viewModel.modelLiveData.value
        val action = ModelDetailFragmentDirections
            .actionModelFragmentToModelRegistrationFragment(
                model?.name ?: "",
                model?.owner?.fullName ?: ""
            )

        // обработка кликов
        when (item.itemId) {
            R.id.registerModel -> findNavController().navigate(action)
        }

        return super.onOptionsItemSelected(item)
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