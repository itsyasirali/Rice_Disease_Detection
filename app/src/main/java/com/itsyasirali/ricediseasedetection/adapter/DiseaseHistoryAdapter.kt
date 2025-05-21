package com.itsyasirali.ricediseasedetection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itsyasirali.ricediseasedetection.databinding.ItemHistoryDiseaseBinding
import com.itsyasirali.ricediseasedetection.models.DiseaseModel
import java.text.SimpleDateFormat
import java.util.Locale

class DiseaseHistoryAdapter(private val diseases: List<DiseaseModel>) :
    RecyclerView.Adapter<DiseaseHistoryAdapter.DiseaseViewHolder>() {

    inner class DiseaseViewHolder(val binding: ItemHistoryDiseaseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryDiseaseBinding.inflate(inflater, parent, false)
        return DiseaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        val item = diseases[position]
        holder.binding.textName.text = item.name
        holder.binding.textDescription.text = item.description

        val formattedDate = item.timestamp.toDate().let {
            val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            formatter.format(it)
        } ?: "No date"

        holder.binding.tvTimeStamp.text = formattedDate
    }

    override fun getItemCount() = diseases.size
}
