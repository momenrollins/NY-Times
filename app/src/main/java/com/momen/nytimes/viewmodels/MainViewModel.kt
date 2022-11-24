package com.momen.nytimes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momen.nytimes.data.NewsResponse
import com.momen.nytimes.repository.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {
    val newsLiveData: LiveData<NewsResponse>
        get() = repository.news

    init {
        callApi()
    }
    fun callApi(){
        viewModelScope.launch {
            repository.getNews()
        }
    }
}