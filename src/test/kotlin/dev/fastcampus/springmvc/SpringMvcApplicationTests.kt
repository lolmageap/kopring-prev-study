package dev.fastcampus.springmvc

import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.repository.ArticleRepository
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

private val logger = KotlinLogging.logger {}

@SpringBootTest
class SpringMvcApplicationTests(
    @Autowired private val articleRepository: ArticleRepository,
) {
    @Test
    fun contextLoads() {
        val prev = articleRepository.count()

        articleRepository.save(
            Article(
                title = "title",
                body = "body",
                authorId = 1L,
            )
        ).let {logger.debug { it }}

        val current = articleRepository.count()
        logger.debug { "prev = $prev, current = $current" }
        Assertions.assertThat(current).isEqualTo(prev + 1)
    }

}
