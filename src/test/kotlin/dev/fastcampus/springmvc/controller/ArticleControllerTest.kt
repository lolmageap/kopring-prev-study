package dev.fastcampus.springmvc.controller

import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.repository.ArticleRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.*


@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:db-init/test.sql")
class ArticleControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val articleRepository: ArticleRepository,
) {

    @Test
    fun get() {
        mockMvc.get("/article/1") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title 1") }
            jsonPath("body") { value("body 1") }
            jsonPath("authorId") { value(1234) }
        }
    }

    @Test
    fun getAll() {
        mockMvc.get("/article") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.length()") { value(1) }
        }
    }

    @Test
    fun create() {
        mockMvc.post("/article") {
            contentType = APPLICATION_JSON
            content = """
                {
                    "title": "title create",
                    "body": "body create",
                    "authorId": "9999"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title create") }
            jsonPath("body") { value("body create") }
            jsonPath("authorId") { value(9999) }
        }
    }

    @Test
    fun update() {
        mockMvc.put("/article/1") {
            contentType = APPLICATION_JSON
            content = """
                {
                    "title": "title update"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title update") }
        }
    }

    @Test
    fun delete() {
        articleRepository.save(
            Article(title = "title", body = "body", authorId = 0)
        )

        mockMvc.delete("/article/1") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }
}