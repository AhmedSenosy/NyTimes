package com.senosy.nytimes.repository

import com.senosy.nytimes.network.models.ArticleResponse
import com.senosy.nytimes.network.models.Result

class FakeArticleRepository:ArticlesRepository {
    private lateinit var articles :ArticleResponse
    private val articlesList : MutableList<Result> = mutableListOf()
    override suspend fun getArticles(period: Int?): ArticleResponse {
            return articles
    }

    fun addArticles(vararg article: Result?){

        article.let {
            for (a in article){
                if (a != null) {
                    articlesList.add(a)
                }
            }
            articles = ArticleResponse("xyz",
                10,articlesList,"OK")
        }

//        articles = ArticleResponse("abc",0, emptyList(),"False")

    }
}