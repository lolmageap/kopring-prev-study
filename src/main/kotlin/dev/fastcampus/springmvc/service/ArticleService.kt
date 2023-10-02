package dev.fastcampus.springmvc.service

import dev.fastcampus.springmvc.exception.ArticleNotFound
import dev.fastcampus.springmvc.model.*
import dev.fastcampus.springmvc.repository.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleService(
    private val articleRepository: ArticleRepository,
) {

    fun get(id: Long) : Article =
        articleRepository.findByIdOrNull(id) ?: throw ArticleNotFound("No article found (id: $id)")

    fun getAll(title: String?) : List<Article> {
        return if (title.isNullOrEmpty()) {
            articleRepository.findAll()
        } else {
            articleRepository.findAllByTitleContains(title)
        }
    }

    fun create(createArticle: CreateArticle) = articleRepository.save( createArticle.toEntity() )

    fun update(id: Long, updateArticle: UpdateArticle) =
        articleRepository.findByIdOrNull(id)
            ?.let { article ->
                updateArticle.title?.let { article.title = it }
                updateArticle.body?.let { article.body = it }
                updateArticle.authorId?.let { article.authorId = it }
                article
            }
            ?: throw ArticleNotFound("No article found (id: $id)")

    fun delete(id: Long) = articleRepository.deleteById(id)

}