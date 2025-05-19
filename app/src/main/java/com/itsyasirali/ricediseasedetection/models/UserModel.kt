package com.itsyasirali.ricediseasedetection.models

import com.google.firebase.Timestamp

data class UserModel(
    var uid: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val timestamp: Timestamp = Timestamp.now()
)
