package com.media.profilecheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.media.profilecheck.repository.BaseRepository
import com.media.profilecheck.repository.MainRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val mainRepository: BaseRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mainRepository as MainRepository) as T
            else -> throw IllegalArgumentException("View Model class not found")
        }
    }


}