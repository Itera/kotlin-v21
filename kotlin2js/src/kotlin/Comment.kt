import org.w3c.dom.Element
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLParagraphElement
import kotlin.browser.document

data class Comment(val postId: Long, val id: Long, val name: String, val email: String, val body: String)

fun commentHTML(comment: Comment): Element {
    val container = document.createElement("div") as HTMLDivElement

    container.className = "comment"

    val name = document.createElement("h3") as HTMLHeadingElement
    val email = document.createElement("h5") as HTMLHeadingElement
    val body = document.createElement("p") as HTMLParagraphElement

    name.innerHTML = comment.name
    email.innerHTML = comment.email
    body.innerHTML = comment.body

    listOf(name, email, body).forEach { container.appendChild(it) }
    return container
}
