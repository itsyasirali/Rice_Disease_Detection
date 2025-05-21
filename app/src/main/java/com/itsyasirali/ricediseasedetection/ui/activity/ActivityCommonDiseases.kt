package com.itsyasirali.ricediseasedetection.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
                name = "bacterial_leaf_blight",
                description = "Caused by Xanthomonas oryzae, leads to yellowing and wilting of leaves."
            ),
            DiseaseModel(
                name = "bacterial_leaf_streak",
                description = "Thin translucent streaks on the leaves, later turning yellow."
            ),
            DiseaseModel(
                name = "bacterial_panicle_blight",
                description = "Affects panicle development, leading to unfilled grains."
            ),
            DiseaseModel(
                name = "blast",
                description = "Fungal disease causing lesions on leaves, collars, and panicles."
            ),
            DiseaseModel(
                name = "brown_spot",
                description = "Brown circular spots with yellow halo, reduces photosynthesis."
            ),
            DiseaseModel(
                name = "dead_heart",
                description = "Central tiller dies off due to stem borer larvae."
            ),
            DiseaseModel(
                name = "downy_mildew",
                description = "Whitish fungal growth on leaves with stunted plant growth."
            ),
            DiseaseModel(
                name = "hispa",
                description = "Blue-black beetle causing white streaks due to scraping leaf surface."
            ),
            DiseaseModel(
                name = "normal",
                description = "Healthy rice plant with no visible signs of infection."
            ),
            DiseaseModel(
                name = "tungro",
                description = "Viral disease transmitted by leafhoppers, results in stunted and yellow-orange plants."
            )
        )

        binding.recyclerDiseases.layoutManager = LinearLayoutManager(this)
        binding.recyclerDiseases.adapter = DiseaseAdapter(diseaseList)
    }
}
