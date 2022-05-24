package org.egorkazantsev.dekonmobile.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.egorkazantsev.dekonmobile.R
import org.egorkazantsev.dekonmobile.databinding.FragmentModelRegistrationBinding

class ModelRegistrationFragment : Fragment() {

    private var _binding: FragmentModelRegistrationBinding? = null
    private val binding get() = _binding!!

    private val args: ModelRegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelRegistrationBinding.inflate(inflater, container, false)

        // изменение заголовка экшн бара
        activity?.title = getString(R.string.model_registration)

        return binding.root
    }
}