package com.itsyasirali.ricediseasedetection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsyasirali.ricediseasedetection.data.Repo
import com.itsyasirali.ricediseasedetection.models.DiseaseModel

class DiseaseViewModel : ViewModel() {

    private val repo = Repo()

    private val _diseases = MutableLiveData<List<DiseaseModel>>()
    val diseases: LiveData<List<DiseaseModel>> get() = _diseases

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _saveStatus = MutableLiveData<String>()
    val saveStatus: LiveData<String> get() = _saveStatus

    fun loadAllDiseases() {
        repo.getAllDiseases(
            onSuccess = { _diseases.postValue(it) },
            onFailure = { _error.postValue(it.message ?: "Unknown error") }
        )
    }

    fun addDisease(disease: DiseaseModel) {
        repo.addDisease(
            disease,
            onSuccess = { _saveStatus.postValue(it) },
            onFailure = { _error.postValue(it.message ?: "Failed to save") }
        )
    }
}
