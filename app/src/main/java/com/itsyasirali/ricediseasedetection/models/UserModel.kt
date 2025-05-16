package com.itsyasirali.ricediseasedetection.models

data class UserModel(
    var uid: String = "",
    val name: String = "",
    val email: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
