package com.example

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

// Inspired by
//  - https://github.com/raharrison/kotlin-ktor-exposed-starter
//  - https://github.com/Kotlin/kotlin-fullstack-sample

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(CallLogging)
    install(CORS) {
        anyHost()
    }

    initDb()

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/comments") {
            call.respond(getComments())
        }

        get("/comments/{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val comment = getComment(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(comment)
        }

        get("/posts/{id}/comments") {
            val postId = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(getCommentsForPost(postId))
        }

        post("/posts/{id}/comments") {
            val postId = call.parameters["id"]?.toInt() ?: return@post call.respond(HttpStatusCode.BadRequest)
            val addedComment = addComment(postId, call.receive())
            call.respond(HttpStatusCode.Created, addedComment)
        }
    }
}

data class Comment(
    val id: Int? = null,
    val postId: Int? = null,
    val name: String,
    val email: String,
    val body: String
)

fun ResultRow.toComment(): Comment {
    return Comment(
        id = this[Comments.id],
        postId = this[Comments.postId],
        name = this[Comments.name],
        email = this[Comments.email],
        body = this[Comments.body]
    )
}

suspend fun getComments(): List<Comment> = dbQuery {
    Comments.selectAll().mapNotNull(ResultRow::toComment)
}

suspend fun getComment(id: Int): Comment? = dbQuery {
    Comments.select { Comments.id eq id }.mapNotNull(ResultRow::toComment).singleOrNull()
}

suspend fun getCommentsForPost(postId: Int): List<Comment> = dbQuery {
    Comments.select { Comments.postId eq postId }.mapNotNull(ResultRow::toComment)
}

suspend fun addComment(postId: Int, comment: Comment): Comment {
    val id: Int? = dbQuery {
        Comments.insert {
            it[Comments.postId] = postId
            it[Comments.name] = comment.name
            it[Comments.email] = comment.email
            it[Comments.body] = comment.body
        } get Comments.id
    }
    return getComment(id!!)!!
}