import org.w3c.dom.HTMLDivElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.js.JSON

val MY_API = "https://jsonplaceholder.typicode.com/comments"

// Uncomment if you have the ktor project running to fetch local comments
// val MY_API = "http://localhost:8080/comments"

fun getAsync(url: String, callback: (String) -> Unit) {
  val xmlHttp = XMLHttpRequest()
  xmlHttp.open("GET", url)

  xmlHttp.onload = {
    if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
      callback.invoke(xmlHttp.responseText)
    }
  }
  xmlHttp.send()
}


fun main(args: Array<String>) {
  val content = document.getElementById("content") as HTMLDivElement

  getAsync(MY_API) { response ->
    val comments = JSON.parse<Array<Comment>>(response)
    comments.forEach { content.appendChild(commentHTML(it)) }
  }
}
