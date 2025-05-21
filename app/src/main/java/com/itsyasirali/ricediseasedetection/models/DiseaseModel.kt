package com.itsyasirali.ricediseasedetection.models

import com.google.firebase.Timestamp

data class DiseaseModel(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    var isExpanded: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
    var userId: String = ""
)
