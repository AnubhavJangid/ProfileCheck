package com.media.profilecheck.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.repository.BaseRepository
import com.media.profilecheck.repository.MainRepository
import com.media.profilecheck.repository.NetworkResult
import com.media.profilecheck.repository.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _users: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val users: LiveData<NetworkResult<UserResponse>>
        get() = _users


    fun getUsers(number: Int) {
        _users.value = NetworkResult.Loading()
        viewModelScope.launch {
            _users.value = mainRepository.getUsersList(number)
        }
    }


}