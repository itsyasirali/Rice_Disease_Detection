package com.itsyasirali.ricediseasedetection.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.itsyasirali.ricediseasedetection.models.UserModel
import com.itsyasirali.ricediseasedetection.util.Constants

class Repo {
    private val constants = Constants
    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection(constants.USER_COLLECTION)

    fun createUser(user: UserModel, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        userCollection
            .add(user)
            .addOnSuccessListener { documentRef ->
                user.uid = documentRef.id
                userCollection.document(documentRef.id).set(user, SetOptions.merge())
                    .addOnSuccessListener {
                        onSuccess("User created successfully")
                    }
                    .addOnFailureListener { onFailure(it) }
            }
            .addOnFailureListener { onFailure(it) }
    }



    fun getAllUsers(onSuccess: (List<UserModel>) -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val users = querySnapshot.documents.mapNotNull { it.toObject(UserModel::class.java) }
                onSuccess(users)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun updateUser(uid: String, updates: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.document(uid)
            .update(updates)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun deleteUser(uid: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userCollection.document(uid)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}
