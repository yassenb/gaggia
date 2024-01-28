import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.send
import kotlinx.coroutines.delay

fun Application.module() {
    install(WebSockets)

    routing {
        webSocket("/graph") {
            while (true) {
                send(Machine.instance.state.temperature.toString())
                delay(1000)
            }
        }
    }
}
