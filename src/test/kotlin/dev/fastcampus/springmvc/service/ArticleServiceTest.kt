package dev.fastcampus.springmvc.service

import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.model.CreateArticle
import dev.fastcampus.springmvc.model.UpdateArticle
import dev.fastcampus.springmvc.repository.ArticleRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class ArticleServiceTest(
//    @Mock private val articleService: ArticleService,
    @Autowired private val articleService: ArticleService,
    @Autowired private val articleRepository: ArticleRepository,
) {

    @Test
    fun createAndGet() {
        val article = articleService.create(
            CreateArticle(title = "title", body = "body", authorId = 9999)
        ).let {
            articleService.get( it.id )
        }

//        Mockito.`when`( articleService.get(1) ).thenReturn(
//            Article(id = 1, title = "title", body = "body", authorId = 9999)
//        )
//        val article = articleService.get(1)

        assertThat(article.title).isEqualTo("title")
        assertThat(article.body).isEqualTo("body")
        assertThat(article.authorId).isEqualTo(9999)
    }

    @Test
    fun getAll() {
        repeat(5) { i ->
            articleRepository.save(
                Article(
                    title = "title $i",
                    body = "body $i",
                    authorId = 1234,
                )
            )
        }

        assertThat(articleService.getAll()).hasSize(5)
        assertThat(articleService.getAll("1")).hasSize(1)
    }

    @Test
    fun update() {
        val articleId = articleRepository.save(
            Article(
                title = "title",
                body = "body",
                authorId = 1234,
            )
        ).id

        val update = articleService.update(
            articleId,
            UpdateArticle(
                title = "update title",
                body = "update body",
                authorId = 4321,
            )
        )

        assertThat(update.title).isEqualTo("update title")
        assertThat(update.body).isEqualTo("update body")
        assertThat(update.authorId).isEqualTo(4321)
    }

    @Test
    fun delete() {
        val articleId = articleRepository.save(
            Article(
                title = "title",
                body = "body",
                authorId = 1234,
            )
        ).id

        articleService.delete(articleId)

        val result = articleRepository.findByIdOrNull(articleId)

        assertThat(result).isNull()
    }
}