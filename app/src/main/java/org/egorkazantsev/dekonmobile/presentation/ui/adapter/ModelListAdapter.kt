package org.egorkazantsev.dekonmobile.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.egorkazantsev.dekonmobile.databinding.ModelItemBinding
import org.egorkazantsev.dekonmobile.domain.model.Model
import java.util.*

class ModelListAdapter(
    private val models: List<Model>,
    private val onModelClickListener: OnModelClickListener
) : RecyclerView.Adapter<ModelListAdapter.ModelViewHolder>() {

    inner class ModelViewHolder(
        val binding: ModelItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        // биндинг всех UI компонентов
        fun bind(model: Model) {
            with(binding) {
                // текста для TextView
                modelNameTextView.text = model.name
                // клик по элементу списка
                root.setOnClickListener(this@ModelViewHolder)
            }
        }

        // обработка кликов
        override fun onClick(view: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                onModelClickListener.onModelClick(models[position].id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(
            ModelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size

    interface OnModelClickListener {
        fun onModelClick(id: UUID)
    }
}