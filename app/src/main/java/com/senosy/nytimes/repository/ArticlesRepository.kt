package com.senosy.nytimes.repository

import com.senosy.nytimes.network.models.ArticleResponse

interface ArticlesRepository {

    suspend fun getArticles(period: Int?): ArticleResponse
}