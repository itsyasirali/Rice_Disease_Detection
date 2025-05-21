package com.itsyasirali.ricediseasedetection.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.adapter.DiseaseHistoryAdapter
import com.itsyasirali.ricediseasedetection.databinding.ActivityHistoryBinding
import com.itsyasirali.ricediseasedetection.viewmodel.DiseaseViewModel

class ActivityHistory : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: DiseaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@ActivityHistory)
        }

        // Observe ViewModel
        viewModel.diseases.observe(this) { list ->
            binding.rvHistory.adapter = DiseaseHistoryAdapter(list)
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        }

        // Fetch diseases
        viewModel.loadAllDiseases()
    }
}
