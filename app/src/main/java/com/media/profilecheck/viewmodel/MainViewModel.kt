package com.media.profilecheck.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.repository.BaseRepository
import com.media.profilecheck.repository.MainRepositoryImpl
import com.media.profilecheck.repository.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepositoryImpl) : ViewModel() {

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