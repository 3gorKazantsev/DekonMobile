package org.egorkazantsev.dekonmobile.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.egorkazantsev.dekonmobile.databinding.ModelItemBinding
import org.egorkazantsev.dekonmobile.domain.model.Model

class ModelListAdapter(
    private val modelList: List<Model>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ModelListAdapter.ViewHolder>() {

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

    inner class ViewHolder(
        val binding: ModelItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                onItemClickListener.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}