package dev.fastcampus.springmvc.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@Entity
class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var body: String,
    var authorId: Long,
): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Article
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Article(id=$id, title='$title', body='$body', authorId=$authorId), ${super.toString()}"
    }

}

@MappedSuperclass
open class BaseEntity(
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
)