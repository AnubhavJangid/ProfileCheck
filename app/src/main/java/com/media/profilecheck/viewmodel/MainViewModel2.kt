package com.media.profilecheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.repository.MainRepositoryImpl
import com.media.profilecheck.repository.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel2 @Inject constructor(private val mainRepository: MainRepositoryImpl) : ViewModel() {

    private val _users: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val users: LiveData<NetworkResult<UserResponse>>
        get() = _users


    fun getUsers(number: Int) {
        _users.value = NetworkResult.Loading()
        viewModelScope.launch {
            _users.value = mainRepository.getUserData(number)
        }
    }

}