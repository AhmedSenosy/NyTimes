package com.senosy.nytimes.repository

import com.senosy.nytimes.network.ArticlesServices
import com.senosy.nytimes.network.apiServices
import com.senosy.nytimes.network.models.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val ArticleRepository by lazy {
    ArticlesRepositoryImpl(webServices = apiServices)
}

class ArticlesRepositoryImpl(
    private val webServices: ArticlesServices,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArticlesRepository {

    override suspend fun getArticles(period: Int?): ArticleResponse {
        return withContext(dispatcher)
        {
            return@withContext webServices.getArticle(period)
        }
    }
}