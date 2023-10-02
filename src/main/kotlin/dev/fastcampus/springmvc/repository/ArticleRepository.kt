package dev.fastcampus.springmvc.repository

import dev.fastcampus.springmvc.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findAllByTitleContains(title: String): List<Article>
}