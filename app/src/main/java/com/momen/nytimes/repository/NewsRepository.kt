package com.momen.nytimes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.momen.nytimes.api.NYTimesApi
import com.momen.nytimes.data.NewsResponse
import com.momen.nytimes.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepository @Inject constructor(private val nyTimesApi: NYTimesApi) {

    private val _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse>
        get() = _news

    suspend fun getNews() {
        try {
            val res = nyTimesApi.getNews("all-sections", 7, Constants.KEY)
            Log.d("TAG", "getNews: ${res.body()}")
            if (res.isSuccessful && res.body() != null) {
                _news.postValue(res.body())
            }
        } catch (e: Exception) {
            _news.postValue(NewsResponse(emptyList(),"failed"))
            Log.e("TAG", "getNews: CATCH ${e.message}")
            e.printStackTrace()
        }

    }

}