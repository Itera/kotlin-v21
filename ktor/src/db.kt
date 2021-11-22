package com.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object Comments : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val postId = integer("postId")
    val name = varchar("name", 1024)
    val email = varchar("email", 1024)
    val body = varchar("body", 1024)
}

fun initDb() {
    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    transaction {
        SchemaUtils.create(Comments)
        addLogger(Slf4jSqlDebugLogger)

        Comments.insert {
            it[postId] = 1
            it[name] = "Hilsen fra Itera"
            it[email] = "kontakt@itera.no"
            it[body] = "Vi håper du har lært litt, og fått smaken på Kotlin!"
        }
        Comments.insert {
            it[postId] = 1
            it[name] = "quo vero reiciendis velit similique earum"
            it[email] = "Jayne_Kuhic@sydney.com"
            it[body] = "est natus enim nihil est dolore omnis voluptatem numquam et omnis occaecati quod ullam at voluptatem error expedita pariatur nihil sint nostrum voluptatem reiciendis et"
        }
        Comments.insert {
            it[postId] = 1
            it[name] = "odio adipisci rerum aut animi"
            it[email] = "Nikita@garfield.biz"
            it[body] = "quia molestiae reprehenderit quasi aspernatur aut expedita occaecati aliquam eveniet laudantium omnis quibusdam delectus saepe quia accusamus maiores nam est cum et ducimus et vero voluptates excepturi deleniti ratione"
        }
        Comments.insert {
            it[postId] = 1
            it[name] = "alias odio sit"
            it[email] = "Lew@alysha.tv"
            it[body] = "non et atque occaecati deserunt quas accusantium unde odit nobis qui voluptatem quia voluptas consequuntur itaque dolor et qui rerum deleniti ut occaecati"
        }
        Comments.insert {
            it[postId] = 1
            it[name] = "vero eaque aliquid doloribus et culpa"
            it[email] = "Hayden@althea.biz"
            it[body] = "harum non quasi et ratione tempore iure ex voluptates in ratione harum architecto fugit inventore cupiditate voluptates magni quo et"
        }
        Comments.insert {
            it[postId] = 2
            it[name] = "et fugit eligendi deleniti quidem qui sint nihil autem"
            it[email] = "Presley.Mueller@myrl.com"
            it[body] = "doloribus at sed quis culpa deserunt consectetur qui praesentium accusamus fugiat dicta voluptatem rerum ut voluptate autem voluptatem repellendus aspernatur dolorem in"
        }
        Comments.insert {
            it[postId] = 2
            it[name] = "repellat consequatur praesentium vel minus molestias voluptatum"
            it[email] = "Dallas@ole.me"
            it[body] = "maiores sed dolores similique labore et inventore et quasi temporibus esse sunt id et eos voluptatem aliquam aliquid ratione corporis molestiae mollitia quia et magnam dolor"
        }
        Comments.insert {
            it[postId] = 2
            it[name] = "et omnis dolorem"
            it[email] = "Mallory_Kunze@marie.org"
            it[body] = "ut voluptatem corrupti velit ad voluptatem maiores et nisi velit vero accusamus maiores voluptates quia aliquid ullam eaque"
        }
        Comments.insert {
            it[postId] = 2
            it[name] = "provident id voluptas"
            it[email] = "Meghan_Littel@rene.us"
            it[body] = "sapiente assumenda molestiae atque adipisci laborum distinctio aperiam et ab ut omnis et occaecati aspernatur odit sit rem expedita quas enim ipsam minus"
        }
        Comments.insert {
            it[postId] = 2
            it[name] = "eaque et deleniti atque tenetur ut quo ut"
            it[email] = "Carmen_Keeling@caroline.name"
            it[body] = "voluptate iusto quis nobis reprehenderit ipsum amet nulla quia quas dolores velit et non aut quia necessitatibus nostrum quaerat nulla et accusamus nisi facilis"
        }
    }
}

suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction { block() }
}
