package com.senosy.nytimes.ui

import android.util.Log
import androidx.lifecycle.*
import com.senosy.nytimes.network.models.ArticleResponse
import com.senosy.nytimes.repository.ArticlesRepository
import com.senosy.nytimes.utils.RequestStatus
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "ArticleViewModel"

class ArticleViewModel(val repo:ArticlesRepository ):ViewModel() {
    private var _articles = MutableLiveData<ArticleResponse>()
    val articles :LiveData<ArticleResponse>
    get() = _articles

    private var loading = MutableLiveData<RequestStatus>()
    val isLoading :LiveData<RequestStatus>
        get() = loading

    fun getArticles(period:Int){
        viewModelScope.launch {
            try {
                loading.value = RequestStatus.Loading
                val result = repo.getArticles(period)
                _articles.value = result
                loading.value = RequestStatus.Success
            }catch (e:Exception){
                Log.e(TAG, "getArticles: ",e)
                loading.value = RequestStatus.Fail
            }
        }
    }

    class Factory(
        private val repository: ArticlesRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArticleViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}