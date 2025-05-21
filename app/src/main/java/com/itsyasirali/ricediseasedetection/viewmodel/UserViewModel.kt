package com.itsyasirali.ricediseasedetection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itsyasirali.ricediseasedetection.data.Repo
import com.itsyasirali.ricediseasedetection.models.UserModel

class UserViewModel : ViewModel() {

    private val repo = Repo()

    private val _allUsers = MutableLiveData<List<UserModel>>()
    val allUsers: LiveData<List<UserModel>> get() = _allUsers

    private val _createStatus = MutableLiveData<String?>()
    val createStatus: LiveData<String?> get() = _createStatus

    private val _updateStatus = MutableLiveData<Boolean>()
    val updateStatus: LiveData<Boolean> get() = _updateStatus

    private val _deleteStatus = MutableLiveData<Boolean>()
    val deleteStatus: LiveData<Boolean> get() = _deleteStatus

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun createUser(user: UserModel) {
        repo.createUser(
            user,
            onSuccess = { message -> _createStatus.postValue(message) },
            onFailure = { exception -> _error.postValue(exception.message) }
        )
    }

    fun     fetchAllUsers() {
        repo.getAllUsers(
            onSuccess = { users -> _allUsers.postValue(users) },
            onFailure = { exception -> _error.postValue(exception.message) }
        )
    }

    fun updateUser(uid: String, updates: Map<String, Any>) {
        repo.updateUser(
            uid, updates,
            onSuccess = { _updateStatus.postValue(true) },
            onFailure = { exception ->
                _updateStatus.postValue(false)
                _error.postValue(exception.message)
            }
        )
    }

    fun deleteUser(uid: String) {
        repo.deleteUser(
            uid,
            onSuccess = { _deleteStatus.postValue(true) },
            onFailure = { exception ->
                _deleteStatus.postValue(false)
                _error.postValue(exception.message)
            }
        )
    }

}
