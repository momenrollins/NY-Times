package com.momen.nytimes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.momen.nytimes.repository.NewsRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}