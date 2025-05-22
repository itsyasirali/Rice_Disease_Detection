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
import com.itsyasirali.ricediseasedetection.util.SharedPrefManager
import com.itsyasirali.ricediseasedetection.viewmodel.DiseaseViewModel

class ActivityHistory : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: DiseaseViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefManager = SharedPrefManager(this)

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@ActivityHistory)
        }

        viewModel.diseases.observe(this) { list ->
            val filteredList = list.filter { it.userId == sharedPrefManager.getUser()?.uid }
            binding.rvHistory.adapter = DiseaseHistoryAdapter(filteredList)
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadAllDiseases()

        binding.btnBack.setOnClickListener { super.onBackPressed() }
    }
}
