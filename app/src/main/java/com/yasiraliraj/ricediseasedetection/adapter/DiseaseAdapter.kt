package com.yasiraliraj.ricediseasedetection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yasiraliraj.ricediseasedetection.R
import com.yasiraliraj.ricediseasedetection.models.DiseaseModel

class DiseaseAdapter(private val diseases: List<DiseaseModel>) :
    RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder>() {

    inner class DiseaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtDiseaseName)
        val desc: TextView = view.findViewById(R.id.txtDiseaseDescription)
        val arrow: ImageView = view.findViewById(R.id.imgArrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_item, parent, false)
        return DiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        val item = diseases[position]
        holder.name.text = item.name.replace('_', ' ').replaceFirstChar { it.uppercaseChar() }
        holder.desc.text = item.description
        holder.desc.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
        holder.arrow.rotation = if (item.isExpanded) 180f else 0f

        holder.itemView.setOnClickListener {
            item.isExpanded = !item.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = diseases.size
}
