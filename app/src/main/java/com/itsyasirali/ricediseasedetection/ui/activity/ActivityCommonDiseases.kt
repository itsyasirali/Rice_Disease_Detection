package com.itsyasirali.ricediseasedetection.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.adapter.DiseaseAdapter
import com.itsyasirali.ricediseasedetection.databinding.ActivityCommonDiseasesBinding
import com.itsyasirali.ricediseasedetection.models.DiseaseModel

class ActivityCommonDiseases : BaseActivity() {

    private lateinit var binding: ActivityCommonDiseasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonDiseasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diseaseList = listOf(
            DiseaseModel(
                name = getString(R.string.disease_bacterial_leaf_blight),
                description = getString(R.string.desc_bacterial_leaf_blight)
            ),
            DiseaseModel(
                name = getString(R.string.disease_bacterial_leaf_streak),
                description = getString(R.string.desc_bacterial_leaf_streak)
            ),
            DiseaseModel(
                name = getString(R.string.disease_bacterial_panicle_blight),
                description = getString(R.string.desc_bacterial_panicle_blight)
            ),
            DiseaseModel(
                name = getString(R.string.disease_blast),
                description = getString(R.string.desc_blast)
            ),
            DiseaseModel(
                name = getString(R.string.disease_brown_spot),
                description = getString(R.string.desc_brown_spot)
            ),
            DiseaseModel(
                name = getString(R.string.disease_dead_heart),
                description = getString(R.string.desc_dead_heart)
            ),
            DiseaseModel(
                name = getString(R.string.disease_downy_mildew),
                description = getString(R.string.desc_downy_mildew)
            ),
            DiseaseModel(
                name = getString(R.string.disease_hispa),
                description = getString(R.string.desc_hispa)
            ),
            DiseaseModel(
                name = getString(R.string.disease_normal),
                description = getString(R.string.desc_normal)
            ),
            DiseaseModel(
                name = getString(R.string.disease_tungro),
                description = getString(R.string.desc_tungro)
            )
        )

        binding.recyclerDiseases.layoutManager = LinearLayoutManager(this)
        binding.recyclerDiseases.adapter = DiseaseAdapter(diseaseList)

        binding.btnBack.setOnClickListener { super.onBackPressed() }
    }
}
