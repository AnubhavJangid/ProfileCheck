package com.media.profilecheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.repository.BaseRepository
import com.media.profilecheck.repository.MainRepositoryImpl
import com.media.profilecheck.repository.NetworkResult
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainViewModel(private val mainRepository: MainRepositoryImpl) : ViewModel() {

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

class MainViewModelFactory @Inject constructor(private val mainRepository: MainRepositoryImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mainRepository) as T
            else -> throw IllegalArgumentException("View Model class not found")
        }
    }
}