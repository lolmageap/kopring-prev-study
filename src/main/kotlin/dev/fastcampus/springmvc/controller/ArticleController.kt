package dev.fastcampus.springmvc.controller

import dev.fastcampus.springmvc.model.CreateArticle
import dev.fastcampus.springmvc.model.UpdateArticle
import dev.fastcampus.springmvc.service.ArticleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService,
) {

    @GetMapping("{id}")
    fun get(@PathVariable id: Long) = articleService.get(id)

    @GetMapping
    fun getAll(@RequestParam title: String?)= articleService.getAll(title)

    @PostMapping
    fun create(@RequestBody createArticle: CreateArticle)= articleService.create(createArticle)

    @PutMapping("{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody updateArticle: UpdateArticle,
    ) = articleService.update(id, updateArticle)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long)= articleService.delete(id)

}