package com.senosy.nytimes.repository

import com.senosy.nytimes.network.models.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesRepositoryImplTest {
    private val result1 = Result("1","aa",12L,"","", emptyList(),
    12, emptyList(),12L, emptyList(),"", emptyList(), emptyList(),"",
    "","","","","","","","")
    private lateinit var repo:FakeArticleRepository
    @Before
    fun createRepo(){
        repo = FakeArticleRepository()
    }

    @Test
    fun getArticles_RequestSuccess()= runBlockingTest{
        repo.addArticles(result1)
        val articles =repo.getArticles(1)

        assertThat(articles.results,IsEqual(listOf(result1)))

    }

    @Test
    fun getArticles_RequestFail()= runBlockingTest{
        repo.addArticles(null)
        val articles =repo.getArticles(1)

        assertThat(articles.results,`is`(not(listOf(result1))))

    }
}