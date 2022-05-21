package org.egorkazantsev.dekonmobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.egorkazantsev.dekonmobile.databinding.ModelItemBinding
import org.egorkazantsev.dekonmobile.domain.model.Model

class ModelListAdapter(val modelList: List<Model>) : RecyclerView.Adapter<ModelListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ModelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modelList[position]
        with(holder.binding) {
            modelNameTextView.text = model.name
        }
    }

    override fun getItemCount() = modelList.size

    class ViewHolder(
        val binding: ModelItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}