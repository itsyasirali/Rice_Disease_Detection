package com.yasiraliraj.ricediseasedetection.models

data class DiseaseModel(
    val name: String,
    val description: String,
    var isExpanded: Boolean = false
)
