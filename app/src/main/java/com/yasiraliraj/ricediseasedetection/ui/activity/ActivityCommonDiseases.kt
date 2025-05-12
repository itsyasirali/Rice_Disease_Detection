package com.yasiraliraj.ricediseasedetection.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasiraliraj.ricediseasedetection.adapter.DiseaseAdapter
import com.yasiraliraj.ricediseasedetection.databinding.ActivityCommonDiseasesBinding
import com.yasiraliraj.ricediseasedetection.models.DiseaseModel

class ActivityCommonDiseases : BaseActivity() {

    private lateinit var binding: ActivityCommonDiseasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonDiseasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diseaseList = listOf(
            DiseaseModel(
                "bacterial_leaf_blight",
                "Caused by Xanthomonas oryzae, leads to yellowing and wilting of leaves."
            ),
            DiseaseModel(
                "bacterial_leaf_streak",
                "Thin translucent streaks on the leaves, later turning yellow."
            ),
            DiseaseModel(
                "bacterial_panicle_blight",
                "Affects panicle development, leading to unfilled grains."
            ),
            DiseaseModel(
                "blast",
                "Fungal disease causing lesions on leaves, collars, and panicles."
            ),
            DiseaseModel(
                "brown_spot",
                "Brown circular spots with yellow halo, reduces photosynthesis."
            ),
            DiseaseModel("dead_heart", "Central tiller dies off due to stem borer larvae."),
            DiseaseModel(
                "downy_mildew",
                "Whitish fungal growth on leaves with stunted plant growth."
            ),
            DiseaseModel(
                "hispa",
                "Blue-black beetle causing white streaks due to scraping leaf surface."
            ),
            DiseaseModel("normal", "Healthy rice plant with no visible signs of infection."),
            DiseaseModel(
                "tungro",
                "Viral disease transmitted by leafhoppers, results in stunted and yellow-orange plants."
            )
        )

        binding.recyclerDiseases.layoutManager = LinearLayoutManager(this)
        binding.recyclerDiseases.adapter = DiseaseAdapter(diseaseList)
    }
}
