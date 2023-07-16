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
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

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

class MainViewModelFactory @Inject constructor(
    private val map : Map<Class<*>, @JvmSuppressWildcards ViewModel>
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}

// if the project structure is increase then the viewmodel class is also increase
// So How can we create a factory class for all the view models ??
// We can do that using multibinding.
// Multibinding is performed in runtime.