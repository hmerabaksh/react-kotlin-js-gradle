import components.app.App
import react.dom.*
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}